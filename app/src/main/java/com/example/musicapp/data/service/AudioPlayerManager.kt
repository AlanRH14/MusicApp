package com.example.musicapp.data.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.example.musicapp.data.service.MusicAppPlaybackService.Companion.KEY_SONG
import com.example.musicapp.domain.model.Artist
import com.example.musicapp.domain.model.AudioStatus
import com.example.musicapp.domain.model.Song
import com.example.musicapp.domain.repository.AudioPlayerRepository
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AudioPlayerManager(
    private val context: Context
) : AudioPlayerRepository {
    private val _audioState = MutableStateFlow(AudioStatus())
    override val audioState: StateFlow<AudioStatus>
        get() = _audioState.asStateFlow()

    private var controllerFuture: ListenableFuture<MediaController>? = null
    private var mediaController: MediaController? = null

    init {
        initializeMediaController()
    }

    private fun initializeMediaController() {
        val sessionToken = SessionToken(
            context,
            ComponentName(context, MusicAppPlaybackService::class.java)
        )

        controllerFuture = MediaController.Builder(context, sessionToken).buildAsync().apply {
            addListener(
                {
                    try {
                        mediaController = get().also { controller ->
                            controller.addListener(playerListener)
                            updateStateFromController(controller)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                },
                MoreExecutors.directExecutor()
            )
        }
    }

    private val playerListener = object : Player.Listener {
        override fun onEvents(player: Player, events: Player.Events) {
            mediaController?.let { updateStateFromController(player) }
        }

        override fun onPlayerError(error: PlaybackException) {
            _audioState.update {
                it.copy(
                    isPlaying = false,
                    isBuffering = false,
                    error = error.localizedMessage ?: "Playback Error"
                )
            }
        }
    }

    private fun updateStateFromController(controller: Player) {
        val mediaItem = controller.currentMediaItem
        val currentSong = if (mediaItem != null) {
            Song(
                id = mediaItem.mediaId,
                title = mediaItem.mediaMetadata.title?.toString().orEmpty(),
                artist = Artist(name = mediaItem.mediaMetadata.artist?.toString().orEmpty()),
                coverImage = mediaItem.mediaMetadata.artworkUri?.toString().orEmpty(),
                genre = mediaItem.mediaMetadata.genre?.toString().orEmpty(),
                audioUrl = mediaItem.localConfiguration?.uri?.toString().orEmpty()
            )
        } else {
            _audioState.value.currentSong
        }
        _audioState.update {
            it.copy(
                isPlaying = controller.isPlaying,
                currentPosition = controller.currentPosition.coerceAtLeast(0),
                currentSong = currentSong ?: it.currentSong,
                duration = controller.duration.coerceAtLeast(0),
                isBuffering = controller.playbackState == Player.STATE_BUFFERING
            )
        }
    }

    override fun playSong(songID: String) {
        _audioState.update { it.copy(isBuffering = true, currentPosition = 0L) }
        val intent = Intent(
            context, MusicAppPlaybackService::class.java
        ).apply {
            action = MusicAppPlaybackService.ACTION_PLAY
            putExtra(KEY_SONG, songID)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent)
        } else {
            context.startService(intent)
        }
    }

    override fun togglePlayPause() {
        val controller = mediaController
        if (controller != null) {
            if (controller.isPlaying) {
                controller.pause()
            } else {
                controller.play()
            }
        } else {
            _audioState.update { it.copy(error = "Playback service not bound") }
        }
    }

    override fun seekTo(position: Long) {
        val controller = mediaController
        if (controller != null) {
            controller.seekTo(position)
            _audioState.update { it.copy(currentPosition = position) }
        } else {
            _audioState.update { it.copy(error = "Playback service not bound") }
        }
    }

    override fun unbindService() {
        mediaController?.removeListener(playerListener)
        mediaController = null
        controllerFuture?.let { MediaController.releaseFuture(it) }
        controllerFuture = null
    }
}
