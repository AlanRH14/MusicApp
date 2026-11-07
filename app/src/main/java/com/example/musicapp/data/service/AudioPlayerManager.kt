package com.example.musicapp.data.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.IBinder
import androidx.lifecycle.viewModelScope
import com.example.musicapp.data.service.MusicAppPlaybackService.Companion.KEY_SONG
import com.example.musicapp.presentation.play_song.mvi.PlaySongState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.collections.copy

class AudioPlayerManager(
    private val context: Context
) {

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private var musicBinder: MusicAppPlaybackService.MusicBinder? = null
    private var isServiceBound = false

    private val _state = MutableStateFlow(PlaySongState())
    val state = _state.asStateFlow()

    private val serviceConnection = object : ServiceConnection {
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

    fun playSong(songID: String) {
        val intent = Intent(
            context, MusicAppPlaybackService::class.java
        ).apply {
                action = MusicAppPlaybackService.ACTION_PLAY
                putExtra(KEY_SONG, songID)
            }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent)
        } else {
            context.startService(intent)
        }

        if (!isServiceBound) {
            val bindIntent = Intent(context, MusicAppPlaybackService::class.java).apply {
                action = MusicAppPlaybackService.ACTION_BIND_CUSTOM
            }
            context.bindService(
                bindIntent,
                serviceConnection,
                Context.BIND_AUTO_CREATE)
        }
    }

    private fun toggleToPause() {
        val service = musicBinder?.getService()
        if (service != null) {
            if (service.player.value.isPlaying) {
                service.pauseSong()
            } else {
                service.resumeSong()
            }
        } else {
            _state.update { it.copy(error = "Playback service not bound") }
        }
    }

    private fun seekTo(position: Long) {
        val service = musicBinder?.getService()
        if (service != null) {
            service.seekTo(position)
        } else {
            _state.update { it.copy(error = "Playback service not bound") }
        }
    }

    private fun observerPlaybackService() {
        val service = musicBinder?.getService() ?: return
        scope.launch {
            service.player.collectLatest { player ->
                _state.update {
                    it.copy(
                        isPlaying = player.isPlaying,
                        currentSong = player.currentSong ?: it.currentSong,
                        currentPosition = player.currentPosition.coerceAtLeast(0),
                        duration = player.duration.coerceAtLeast(0),
                        isBuffering = player.isBuffering,
                        error = player.error ?: it.error
                    )
                }
            }
        }
    }

    fun unbind() {
        if (isServiceBound) {
            context.unbindService(serviceConnection)
            isServiceBound = false
            musicBinder = null
        }
    }
}
