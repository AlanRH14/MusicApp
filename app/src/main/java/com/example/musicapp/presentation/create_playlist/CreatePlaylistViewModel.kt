package com.example.musicapp.presentation.create_playlist

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class CreatePlaylistViewModel : ViewModel() {

    private val _state = MutableStateFlow(CreatePlaylistState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<CreatePlaylistEffect>()
    val effect = _effect.asSharedFlow()

    fun onEvent(event: CreatePlaylistUIEvent) {
    }
}