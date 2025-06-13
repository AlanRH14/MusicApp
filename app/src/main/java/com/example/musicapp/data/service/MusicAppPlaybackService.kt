package com.example.musicapp.data.service

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MusicAppPlaybackService: Service() {

    companion object {
        private const val ACTION_PLAY = "com.example.musicapp.ACTION_PLAY"
        private const val ACTION_PAUSE = "com.example.musicapp.ACTION_PAUSE"
        private const val ACTION_STOP = "com.example.musicapp.ACTION_STOP"
        private const val ACTION_PREVIOUS = "com.example.musicapp.ACTION_PREVIOUS"
        private const val ACTION_NEXT = "com.example.musicapp.ACTION_NEXT"
        private const val ACTION_PREPARE_SONG = "com.example.musicapp.ACTION_PREPAPRE_SONG"
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}