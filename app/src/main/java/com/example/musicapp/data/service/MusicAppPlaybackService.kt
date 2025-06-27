package com.example.musicapp.data.service

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.core.net.toUri
import androidx.media.session.MediaButtonReceiver
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
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

class MusicAppPlaybackService : Service() {

    companion object {
        const val ACTION_PLAY = "com.example.musicapp.ACTION_PLAY"
        const val ACTION_PAUSE = "com.example.musicapp.ACTION_PAUSE"
        const val ACTION_STOP = "com.example.musicapp.ACTION_STOP"
        const val ACTION_PREVIOUS = "com.example.musicapp.ACTION_PREVIOUS"
        const val ACTION_NEXT = "com.example.musicapp.ACTION_NEXT"
        const val ACTION_PREPARE_SONG = "com.example.musicapp.ACTION_PREPAPRE_SONG"

        const val KEY_SONG = "SONG"
    }

    inner class MusicBinder : Binder() {
        fun getService(): MusicAppPlaybackService = this@MusicAppPlaybackService
    }

    private val binder = MusicBinder()
    private val serviceScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private lateinit var exoPlayer: ExoPlayer
    private lateinit var mediaSession: MediaSessionCompat
    private val notificationHelper: MusicAppNotificationHelper by inject()
    private val _player = MutableStateFlow(PlayerState())
    val player = _player.asStateFlow()
    private var positionUpdateJob: Job? = null
    private var notificationJob: Job? = null
    var isForegroundService = false
    var currentNotification: Notification? = null

    val playerListener = object : Player.Listener {
        override fun onPlaybackStateChanged(playbackState: Int) {
            when (playbackState) {
                Player.STATE_BUFFERING -> {
                    _player.update {
                        it.copy(
                            isBuffering = true,
                            currentPosition = exoPlayer.currentPosition,
                            duration = exoPlayer.duration,
                            error = null,
                            isPlaying = false
                        )
                    }
                    updatePlaybackState(PlaybackStateCompat.STATE_BUFFERING)
                }

                Player.STATE_READY -> {
                    _player.update {
                        it.copy(
                            isPlaying = exoPlayer.isPlaying,
                            currentPosition = exoPlayer.currentPosition,
                            duration = exoPlayer.duration,
                            error = null,
                            isBuffering = false
                        )
                    }
                    if (exoPlayer.isPlaying) {
                        startForegroundServiceIfNeeded()
                        updatePlaybackState(PlaybackStateCompat.STATE_PLAYING)
                    } else {
                        updatePlaybackState(PlaybackStateCompat.STATE_PAUSED)
                    }
                }

                Player.STATE_ENDED -> {
                    _player.update {
                        it.copy(
                            isPlaying = false,
                            currentPosition = 0L,
                            duration = 0L,
                            error = null,
                            isBuffering = false
                        )
                    }
                    updatePlaybackState(PlaybackStateCompat.STATE_STOPPED)
                }

                Player.STATE_IDLE -> {
                    _player.update {
                        it.copy(
                            isPlaying = false,
                            currentPosition = 0L,
                            duration = 0L,
                            error = null,
                            isBuffering = false
                        )
                    }
                    updatePlaybackState(PlaybackStateCompat.STATE_NONE)
                }
            }

            updateMediaSessionState()
        }
    }

    private fun updatePlaybackState(stateBuffering: Int) {
        val state = PlaybackStateCompat.Builder()
            .setState(
                stateBuffering,
                exoPlayer.currentPosition,
                1f
            ).setActions(
                PlaybackStateCompat.ACTION_PLAY or
                        PlaybackStateCompat.ACTION_PAUSE or
                        PlaybackStateCompat.ACTION_STOP or
                        PlaybackStateCompat.ACTION_SEEK_TO or
                        PlaybackStateCompat.ACTION_SKIP_TO_NEXT or
                        PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS
            ).build()

        mediaSession.setPlaybackState(state)
    }

    private fun updateMediaSessionState() {
        if (exoPlayer.isPlaying || _player.value.currentSong != null) {
            if (!mediaSession.isActive) {
                mediaSession.isActive = true
            }
        } else {
            if (mediaSession.isActive) {
                mediaSession.isActive = false
            }
        }
    }

    val mediaSessionCallback = object : MediaSessionCompat.Callback() {
        override fun onPlay() {
            resumeSong()
        }

        override fun onPause() {
            pauseSong()
        }

        override fun onStop() {
            super.onStop()
        }

        override fun onSkipToNext() {
            super.onSkipToNext()
        }

        override fun onSkipToPrevious() {
            super.onSkipToPrevious()
        }

        override fun onSeekTo(pos: Long) {
            super.onSeekTo(pos)
            exoPlayer.seekTo(pos)
            _player.update {
                it.copy(
                    currentPosition = pos,
                    duration = exoPlayer.duration,
                    isBuffering = exoPlayer.isLoading,
                    isPlaying = exoPlayer.isPlaying,
                    error = null
                )
            }
        }
    }

    override fun onCreate() {
        super.onCreate()

        exoPlayer = ExoPlayer.Builder(this).build().also {
            it.playWhenReady = true
            it.addListener(playerListener)
        }

        mediaSession = MediaSessionCompat(this, "MusicAppPlaybackService").also {
            it.isActive = true
            it.setCallback(mediaSessionCallback)
            it.setPlaybackState(
                PlaybackStateCompat.Builder().setState(PlaybackStateCompat.STATE_NONE, 0, 0F)
                    .setActions(
                        PlaybackStateCompat.ACTION_PLAY or
                                PlaybackStateCompat.ACTION_PAUSE or
                                PlaybackStateCompat.ACTION_STOP or
                                PlaybackStateCompat.ACTION_SEEK_TO or
                                PlaybackStateCompat.ACTION_SKIP_TO_NEXT or
                                PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS
                    ).build()
            )
        }
        startPositionUpdate()
    }

    private fun startPositionUpdate() {
        positionUpdateJob?.cancel()
        positionUpdateJob = serviceScope.launch {
            while (true) {
                if (exoPlayer.isPlaying) {
                    _player.update {
                        it.copy(
                            currentPosition = exoPlayer.currentPosition,
                            duration = exoPlayer.duration,
                            isBuffering = exoPlayer.isLoading,
                            isPlaying = exoPlayer.isPlaying,
                            error = null
                        )
                    }
                }
                delay(500)
            }
        }
    }

    fun startForegroundServiceIfNeeded() {
        val currentSong = player.value.currentSong ?: return

        if (!isForegroundService) {
            notificationHelper.createPlayerNotification(
                player.value.isPlaying,
                currentSong,
                mediaSession
            ) {
                if (!isForegroundService) {
                    try {
                        currentNotification = it
                        startForeground(
                            MusicAppNotificationHelper.NOTIFICATION_ID,
                            it
                        )
                        isForegroundService = true
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } else {
                    currentNotification = it
                    updateNotification()
                }
            }
        } else {
            updateNotification()
        }
    }

    fun stopForegroundServiceIfNeeded() {
        if (isForegroundService) {
            try {
                mediaSession.isActive = false
                stopForeground(STOP_FOREGROUND_REMOVE)
                isForegroundService = false
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateNotification() {
        notificationJob?.cancel()
        notificationJob = serviceScope.launch {
            delay(500)
            notificationHelper.createPlayerNotification(
                player.value.isPlaying,
                player.value.currentSong ?: return@launch,
                mediaSession
            ) {
                try {
                    currentNotification = it
                    notificationHelper.updateNotification(it)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent != null) {
            MediaButtonReceiver.handleIntent(mediaSession, intent)
        }

        when (intent?.action) {
            ACTION_PLAY -> {
                val song = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    intent.getParcelableExtra(KEY_SONG, Song::class.java)
                } else {
                    intent.getParcelableExtra(KEY_SONG)
                }

                if (song != null) {
                    playSong(song)
                }
            }

            ACTION_PAUSE -> pauseSong()

            ACTION_STOP -> 0

            ACTION_PREVIOUS -> 0

            ACTION_NEXT -> 0

            ACTION_PREPARE_SONG -> 0

            else -> 0
        }

        return START_STICKY
    }

    fun playSong(song: Song) {
        try {
            _player.update {
                it.copy(
                    isPlaying = true,
                    currentSong = song,
                    currentPosition = 0L,
                    duration = song.duration.toLong()
                )
            }

            val metaBuilder = MediaMetadataCompat.Builder()
                .putString(MediaMetadataCompat.METADATA_KEY_TITLE, song.title)
                .putString(MediaMetadataCompat.METADATA_KEY_ARTIST, song.artist.name)
                .putLong(MediaMetadataCompat.METADATA_KEY_DURATION, song.duration.toLong())
                .putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON_URI, song.coverImage)
                .putString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID, song.id)

            mediaSession.setMetadata(metaBuilder.build())
            val mediaItem = MediaItem.fromUri(song.coverImage.toUri())
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.prepare()
            exoPlayer.playWhenReady = true
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

    fun pauseSong() {
        try {
            _player.update {
                it.copy(
                    isPlaying = false,
                    currentPosition = exoPlayer.currentPosition,
                    duration = exoPlayer.duration
                )
            }
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
        }
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }
}