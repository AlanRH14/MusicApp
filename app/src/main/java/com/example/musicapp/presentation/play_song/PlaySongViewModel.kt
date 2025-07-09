package com.example.musicapp.presentation.play_song

import android.app.Application
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.IBinder
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.common.Resource
import com.example.musicapp.data.service.MusicAppPlaybackService
import com.example.musicapp.data.service.MusicAppPlaybackService.Companion.KEY_SONG
import com.example.musicapp.domain.model.Song
import com.example.musicapp.domain.repository.MusicRepository
import com.example.musicapp.presentation.play_song.mvi.PlaySongEffect
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlaySongViewModel(
    private val mContext: Application,
    private val repository: MusicRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(PlaySongState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<PlaySongEffect>()
    val effect = _effect.asSharedFlow()

    private var playbackService: MusicAppPlaybackService? = null
    private var isServiceBound = false
    private var currentSong: Song? = null

    fun onEvent(event: PlaySongUIEvent) {
        when (event) {
            is PlaySongUIEvent.GetSongByID -> getSongByID(event.songID)
        }
    }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            isServiceBound = true
            playbackService = (binder as MusicAppPlaybackService.MusicBinder).getService()
            currentSong?.let {
                startServiceAndBind(it)
            } ?: run {
                _state.update { it.copy(error = "No song to play") }
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isServiceBound = false
            playbackService = null
        }
    }

    private fun observerPlaybackService() {
        playbackService?.let { service ->
            viewModelScope.launch {
                service.player.onEach { player ->
                    _state.update {
                        it.copy(
                            isPlaying = player.isPlaying,
                            currentSong = player.currentSong,
                            currentPosition = player.currentPosition.coerceAtLeast(0),
                            duration = player.duration.coerceAtLeast(0),
                            isBuffering = player.isBuffering,
                            error = player.error
                        )
                    }
                }.launchIn(viewModelScope)
            }
        }
    }

    private fun getSongByID(id: String) {
        viewModelScope.launch {
            when (val response = repository.getSongById(id = id)) {
                is Resource.Loading -> {
                    _state.update {
                        it.copy(isLoading = true)
                    }
                }

                is Resource.Success -> {
                    startServiceAndBind(song = response.data)
                    _effect.emit(PlaySongEffect.ShowErrorMessage("Success"))
                }

                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = response.message
                        )
                    }
                }
            }
        }
    }

    fun toggleToPause() {
        playbackService?.let { service ->
            if (service.player.value.isPlaying) {
                service.pauseSong()
            } else {
                currentSong?.let { song ->
                    service.playSong(song)
                } ?: run {
                    _state.update { it.copy(error = "No song to play") }
                }
            }
        } ?: run {
            _state.update { it.copy(error = "Playback service not bound") }
        }
    }

    private fun startServiceAndBind(song: Song) {
        val intent = Intent(
            mContext, MusicAppPlaybackService::class.java
        ).apply {
            action = MusicAppPlaybackService.ACTION_PLAY
            putExtra(KEY_SONG, song)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mContext.startForegroundService(intent)
        } else {
            mContext.startService(intent)
        }

        if (!isServiceBound) {
            mContext.bindService(
                Intent(mContext, MusicAppPlaybackService::class.java),
                serviceConnection,
                Context.BIND_AUTO_CREATE
            )
        }
    }
}