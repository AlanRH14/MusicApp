package com.example.musicapp.data.service

import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.os.IBinder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class AudioPlayerManager(
    private val context: Context
) {

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private var musicBinder: MusicAppPlaybackService.MusicBinder? = null
    private var isServiceBound = false

    private val _service = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            isServiceBound = true
            if (binder is MusicAppPlaybackService.MusicBinder) {
                musicBinder = binder
                observerPlaybackService()
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isServiceBound = false
            musicBinder = null
        }
    }


}