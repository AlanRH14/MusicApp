package com.example.musicapp.presentation.play_song

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.common.Resource
import com.example.musicapp.data.service.MusicAppPlaybackService
import com.example.musicapp.data.service.MusicAppPlaybackService.Companion.KEY_SONG
import com.example.musicapp.domain.model.Song
import com.example.musicapp.domain.repository.MusicRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlaySongViewModel(
    private val mContext: Context,
    private val repository: MusicRepository
) : ViewModel() {

    private val _state = MutableStateFlow(PlaySongState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<PlaySongEffect>()
    val effect = _effect.asSharedFlow()

    fun onEvent(event: PlaySongUIEvent) {
        when (event) {
            is PlaySongUIEvent.GetSongByID -> getSongByID(event.songID)
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
                    playSong(song = response.data)
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

    private fun playSong(song: Song) {
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
    }
}