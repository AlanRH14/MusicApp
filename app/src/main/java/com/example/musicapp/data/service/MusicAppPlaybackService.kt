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
    }

    inner class MusicBinder() : Binder() {
        fun getService(): MusicAppPlaybackService = this@MusicAppPlaybackService
    }

    private val binder = MusicBinder()
    private val serviceScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private lateinit var exoPlayer: ExoPlayer
    private lateinit var mediaSession: MediaSessionCompat
    private val notificationHelper: MusicAppNotificationHelper by inject()

    val playerListener = object : Player.Listener {}
    val mediaSessionCallback = object : MediaSessionCompat.Callback() {}

    private val _player = MutableStateFlow(PlayerState())
    val player = _player.asStateFlow()

    private var positionUpdateJob: Job? = null
    private var notificationJob: Job? = null

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
                                PlaybackStateCompat.ACTION_STOP or PlaybackStateCompat.ACTION_SKIP_TO_NEXT
                    ).build()
            )
        }
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
                            isPlaying = exoPlayer.isPlaying
                        )
                    }
                }
                delay(500)
            }
        }
    }

    var isForegroundService = false
    var currentNotification: Notification? = null

    fun startForegroundServiceIfNeeded() {
        val currentSong = player.value.currentSong ?: return

        if (!isForegroundService) {
            notificationHelper.createPlayerNotification(
                player.value.isPlaying,
                currentSong,
                mediaSession
            ) {
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
                    intent.getParcelableExtra("SONG", Song::class.java)
                } else {
                    intent.getParcelableExtra("SONG")
                }

                if (song != null) {
                    playSong(song)
                }
            }

            ACTION_PAUSE -> {
                0
            }

            ACTION_STOP -> {
                0
            }

            ACTION_PREVIOUS -> {
                0
            }

            ACTION_NEXT -> {
                0
            }

            ACTION_PREPARE_SONG -> {
                0
            }

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
            e.printStackTrace()
        }
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }
}