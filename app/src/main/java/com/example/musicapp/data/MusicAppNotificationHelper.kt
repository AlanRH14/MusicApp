package com.example.musicapp.data

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

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
}