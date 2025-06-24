package com.example.musicapp.data.service

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.musicapp.data.service.helper.MusicAppNotificationHelper
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

    var positionUpdateJob: Job? = null

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

    }

    fun updateNotification() {

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_PLAY -> {
                0
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

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }
}