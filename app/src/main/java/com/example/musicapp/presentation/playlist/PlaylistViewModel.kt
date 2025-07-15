package com.example.musicapp.presentation.playlist

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class PlaylistViewModel : ViewModel() {

    private val _state = MutableStateFlow(PlaylistState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<PlaylistEffect>()
    val effect = _effect.asSharedFlow()


}