package com.example.musicapp.presentation.playlist_detail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class PlaylistDetailViewModel : ViewModel() {

    private val _state = MutableStateFlow(PlaylistDetailState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<PlaylistDetailEffect>()
    val effect = _effect.asSharedFlow()

    fun onEvent(event: PlaylistDetailUIEvent) {

    }
}