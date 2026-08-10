package com.example.musicapp.data.service

import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import com.example.musicapp.data.service.helper.MusicAppNotificationHelper
import com.example.musicapp.domain.model.Song
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import kotlin.time.Duration.Companion.milliseconds

class MusicAppPlaybackService : MediaSessionService() {

    companion object {
        const val ACTION_PLAY = "com.example.musicapp.ACTION_PLAY"
        const val ACTION_PAUSE = "com.example.musicapp.ACTION_PAUSE"
        const val ACTION_STOP = "com.example.musicapp.ACTION_STOP"
        const val ACTION_PREVIOUS = "com.example.musicapp.ACTION_PREVIOUS"
        const val ACTION_NEXT = "com.example.musicapp.ACTION_NEXT"
        const val ACTION_BIND_CUSTOM = "com.example.musicapp.ACTION_BIND_CUSTOM"

        const val KEY_SONG = "SONG"
    }

    inner class MusicBinder : Binder() {
        fun getService(): MusicAppPlaybackService = this@MusicAppPlaybackService
    }

    private val binder = MusicBinder()
    private val serviceScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private lateinit var exoPlayer: ExoPlayer
    private var mediaSession: MediaSession? = null
    private val notificationHelper: MusicAppNotificationHelper by inject()
    private val _player = MutableStateFlow(PlayerState())
    val player = _player.asStateFlow()
    private var positionUpdateJob: Job? = null
    private var isForegroundService = false

    private val playerListener = object : Player.Listener {
        override fun onPlaybackStateChanged(playbackState: Int) {
            _player.update {
                it.copy(
                    isBuffering = true,
                    currentPosition = exoPlayer.currentPosition,
                    duration = exoPlayer.duration,
                    isPlaying = false
                )
            }

            if (exoPlayer.isPlaying) startForegroundServiceIfNeeded()
        }

        override fun onIsPlayingChanged(isPlaying: Boolean) {
            _player.update { it.copy(isPlaying = isPlaying) }
            if (isPlaying) {
                startForegroundServiceIfNeeded()
            } else {
                updateNotification()
            }
        }
    }

    override fun onCreate() {
        super.onCreate()

        exoPlayer = ExoPlayer.Builder(this).build().also {
            it.playWhenReady = true
            it.addListener(playerListener)
        }

        mediaSession = MediaSession.Builder(this, exoPlayer).build()
        startPositionUpdate()
    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession? =
        mediaSession

    private fun startPositionUpdate() {
        positionUpdateJob?.cancel()
        positionUpdateJob = serviceScope.launch {
            while (true) {
                if (exoPlayer.isPlaying) {
                    _player.update {
                        it.copy(
                            currentPosition = exoPlayer.currentPosition,
                            duration = exoPlayer.duration,
                        )
                    }
                }
                delay(500.milliseconds)
            }
        }
    }

    fun startForegroundServiceIfNeeded() {
        val song = player.value.currentSong ?: return
        val session = mediaSession ?: return

        notificationHelper.createPlayerNotification(
            player.value.isPlaying, song, session
        ) {
            if (!isForegroundService) {
                try {
                    startForeground(MusicAppNotificationHelper.NOTIFICATION_ID, it)
                    isForegroundService = true
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {
                notificationHelper.updateNotification(it)
            }
        }
    }

    private fun updateNotification() {
        val song = player.value.currentSong ?: return
        val session = mediaSession ?: return

        notificationHelper.createPlayerNotification(
            player.value.isPlaying,
            song,
            session
        ) {
            try {
                notificationHelper.updateNotification(it)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)

        when (intent?.action) {
            ACTION_PLAY -> {
                val song = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    intent.getParcelableExtra(KEY_SONG, Song::class.java)
                } else {
                    intent.getParcelableExtra(KEY_SONG)
                }
                song?.let { playSong(it) } ?: resumeSong()
            }

            ACTION_PAUSE -> pauseSong()
            ACTION_STOP -> stopSelf()
        }

        return START_STICKY
    }

    private fun playSong(song: Song) {
        try {
            _player.update { it.copy(currentSong = song, isBuffering = true) }

            val mediaItem = MediaItem.Builder()
                .setUri(song.audioUrl.toUri())
                .setMediaId(song.id)
                .setMediaMetadata(
                    MediaMetadata.Builder()
                        .setTitle(song.title)
                        .setArtist(song.artist.name)
                        .setArtworkUri(song.coverImage.toUri())
                        .build()
                ).build()

            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.prepare()
            exoPlayer.play()
        } catch (e: Exception) {
            _player.update {
                it.copy(
                    error = e.message,
                    isBuffering = false,
                    currentSong = null
                )
            }
            e.printStackTrace()
        }
    }

    fun seekTo(position: Long) {
        try {
            exoPlayer.seekTo(position)
            _player.update {
                it.copy(
                    currentPosition = position,
                    duration = exoPlayer.duration
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun pauseSong() {
        try {
            exoPlayer.pause()
            _player.update {
                it.copy(
                    isPlaying = false,
                    currentPosition = exoPlayer.currentPosition,
                    duration = exoPlayer.duration
                )
            }
            updateNotification()
        } catch (e: Exception) {
            _player.update {
                it.copy(
                    error = e.message,
                    isBuffering = false,
                    currentSong = null
                )
            }
            e.printStackTrace()
        }
    }

    fun resumeSong() {
        try {
            exoPlayer.play()
            _player.update {
                it.copy(
                    isPlaying = true,
                    currentPosition = exoPlayer.currentPosition,
                    duration = exoPlayer.duration
                )
            }
            startForegroundServiceIfNeeded()
        } catch (e: Exception) {
            _player.update {
                it.copy(
                    error = e.message,
                    isBuffering = false,
                    currentSong = null
                )
            }
            e.printStackTrace()
        }
        updateNotification()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return if (intent?.action == ACTION_BIND_CUSTOM) {
            binder
        } else {
            super.onBind(intent)
        }
    }

    override fun onDestroy() {
        exoPlayer.release()
        mediaSession?.release()
        mediaSession = null
        super.onDestroy()
    }
}