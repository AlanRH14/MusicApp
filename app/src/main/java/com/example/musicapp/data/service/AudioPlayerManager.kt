package com.example.musicapp.data.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.IBinder
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.example.musicapp.data.service.MusicAppPlaybackService.Companion.KEY_SONG
import com.example.musicapp.domain.model.AudioStatus
import com.example.musicapp.domain.repository.AudioPlayerRepository
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AudioPlayerManager(
    private val context: Context
) : AudioPlayerRepository {

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private var isServiceBound = false

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
        override fun onIsPlayingChanged(isPlaying: Boolean) {
            mediaController?.let { updateStateFromController(it) }
        }

        override fun onPlaybackStateChanged(playbackState: Int) {
            mediaController?.let { updateStateFromController(it) }
        }

        override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
            mediaController?.let { updateStateFromController(it) }
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
        _audioState.update {
            it.copy(
                isPlaying = controller.isPlaying,
                currentPosition = controller.currentPosition.coerceAtLeast(0),
                duration = controller.duration.coerceAtLeast(0),
                isBuffering = controller.playbackState == Player.STATE_BUFFERING
            )
        }
    }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            isServiceBound = true
            if (binder is MusicAppPlaybackService.MusicBinder) {
                musicBinder = binder
                observerPlaybackService()
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isServiceBound = false
            musicBinder = null
        }
    }

    override fun playSong(songID: String) {
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

        if (!isServiceBound) {
            val bindIntent = Intent(context, MusicAppPlaybackService::class.java).apply {
                action = MusicAppPlaybackService.ACTION_BIND_CUSTOM
            }
            context.bindService(
                bindIntent,
                serviceConnection,
                Context.BIND_AUTO_CREATE
            )
        }
    }

    override fun togglePlayPause() {
        val service = musicBinder?.getService()
        if (service != null) {
            if (service.player.value.isPlaying) {
                service.pauseSong()
            } else {
                service.resumeSong()
            }
        } else {
            _audioState.update { it.copy(error = "Playback service not bound") }
        }
    }

    override fun seekTo(position: Long) {
        val service = musicBinder?.getService()
        if (service != null) {
            service.seekTo(position)
        } else {
            _audioState.update { it.copy(error = "Playback service not bound") }
        }
    }

    private fun observerPlaybackService() {
        val service = musicBinder?.getService() ?: return
        scope.launch {
            service.player.collectLatest { player ->
                _audioState.update {
                    it.copy(
                        isPlaying = player.isPlaying,
                        currentSong = player.currentSong ?: it.currentSong,
                        currentPosition = player.currentPosition.coerceAtLeast(0),
                        duration = player.duration.coerceAtLeast(0),
                        isBuffering = player.isBuffering,
                        error = player.error ?: it.error
                    )
                }
            }
        }
    }

    override fun unbindService() {
        if (isServiceBound) {
            context.unbindService(serviceConnection)
            isServiceBound = false
            musicBinder = null
        }
    }
}
