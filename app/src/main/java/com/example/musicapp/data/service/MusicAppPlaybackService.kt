package com.example.musicapp.data.service

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MusicAppPlaybackService: Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}