package com.example.musicapp.data.service

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MusicAppPlaybackService : Service() {

    companion object {
        const val ACTION_PLAY = "com.example.musicapp.ACTION_PLAY"
        const val ACTION_PAUSE = "com.example.musicapp.ACTION_PAUSE"
        const val ACTION_STOP = "com.example.musicapp.ACTION_STOP"
        const val ACTION_PREVIOUS = "com.example.musicapp.ACTION_PREVIOUS"
        const val ACTION_NEXT = "com.example.musicapp.ACTION_NEXT"
        const val ACTION_PREPARE_SONG = "com.example.musicapp.ACTION_PREPAPRE_SONG"
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

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}