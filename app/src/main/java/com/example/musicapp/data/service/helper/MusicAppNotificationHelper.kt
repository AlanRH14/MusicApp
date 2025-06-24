package com.example.musicapp.data.service.helper

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.media.session.MediaSessionCompat
import androidx.core.app.NotificationCompat
import com.example.musicapp.MainActivity
import com.example.musicapp.R
import com.example.musicapp.data.service.MusicAppPlaybackService
import com.example.musicapp.domain.model.Song

class MusicAppNotificationHelper(private val mContext: Context) {

    companion object {
        private const val CHANNEL_ID = "musicapp_notification_channel"
        private const val CHANNEL_NAME = "Notification Channel"
        private const val CHANNEL_DESCRIPTION = "Channel for MusicApp playback notifications"
        const val NOTIFICATION_ID = 1

        fun createNotificationChannel(mContext: Context) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT,
                ).apply {
                    description = CHANNEL_DESCRIPTION
                    setSound(null, null)
                    setShowBadge(false)
                    lockscreenVisibility = Notification.VISIBILITY_PUBLIC
                }

                val notificationManager =
                    mContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }
        }
    }

    fun createPlayerNotification(
        isPlaying: Boolean,
        songDto: Song,
        mediasSession: MediaSessionCompat,
        callback: (Notification) -> Unit,
    ) {
        val intent = Intent(mContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        }

        val pendingIntent = PendingIntent.getActivity(
            mContext,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notificationBuilder = NotificationCompat.Builder(mContext, CHANNEL_ID)
            .setContentTitle(songDto.title)
            .setContentText(songDto.artist.name)
            .setSmallIcon(R.drawable.ic_profile)
            .setContentIntent(pendingIntent)
            .setStyle(
                androidx.media.app.NotificationCompat.MediaStyle()
                    .setMediaSession(mediasSession.sessionToken)
                    .setShowActionsInCompactView(0)
            )
            .setOngoing(true)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setAutoCancel(false)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setForegroundServiceBehavior(NotificationCompat.FOREGROUND_SERVICE_IMMEDIATE)

        val prevIntent = Intent(mContext, MainActivity::class.java).apply {
            action = MusicAppPlaybackService.ACTION_PREVIOUS
        }
        val nextIntent = Intent(mContext, MainActivity::class.java).apply {
            action = MusicAppPlaybackService.ACTION_NEXT
        }
        val playIntent = Intent(mContext, MainActivity::class.java).apply {
            action = MusicAppPlaybackService.ACTION_PLAY
        }
        val pauseIntent = Intent(mContext, MusicAppPlaybackService.ACTION_PAUSE::class.java).apply {
            action = MusicAppPlaybackService.ACTION_PAUSE
        }

        val prevPendingIntent = PendingIntent.getActivity(
            mContext,
            0,
            prevIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val prevNextIntent = PendingIntent.getActivity(
            mContext,
            0,
            nextIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val prevPlayIntent = PendingIntent.getActivity(
            mContext,
            0,
            playIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val prevPauseIntent = PendingIntent.getActivity(
            mContext,
            0,
            pauseIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        notificationBuilder.addAction(
            NotificationCompat.Action(
                android.R.drawable.ic_media_previous,
                "Previous",
                prevPendingIntent
            )
        )

        notificationBuilder.addAction(
            NotificationCompat.Action(
                android.R.drawable.ic_media_next,
                "Next",
                prevNextIntent
            )
        )

        if (isPlaying) {
            notificationBuilder.addAction(
                NotificationCompat.Action(
                    android.R.drawable.ic_media_play,
                    "Play",
                    prevPlayIntent
                )
            )
        } else {
            notificationBuilder.addAction(
                NotificationCompat.Action(
                    android.R.drawable.ic_media_pause,
                    "Pause",
                    prevPauseIntent
                )
            )
        }

        val notification = notificationBuilder.build()
        notification.flags = Notification.FLAG_NO_CLEAR or Notification.FLAG_ONGOING_EVENT
        callback(notification)
    }

    fun loadAlbumIcon() {

    }
}