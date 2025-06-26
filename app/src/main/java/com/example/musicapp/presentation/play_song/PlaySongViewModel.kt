package com.example.musicapp.presentation.play_song

import androidx.lifecycle.ViewModel
import com.example.musicapp.domain.repository.MusicRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class PlaySongViewModel(
    private val repository: MusicRepository
) : ViewModel() {

    private val _state = MutableStateFlow(PlaySongState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<PlaySongEffect>()
    val effect = _effect.asSharedFlow()

    fun onEvent(event: PlaySongUIEvent) {
        when(event) {
            else -> Unit
        }
    }
}