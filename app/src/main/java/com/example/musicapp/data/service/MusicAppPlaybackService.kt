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

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}