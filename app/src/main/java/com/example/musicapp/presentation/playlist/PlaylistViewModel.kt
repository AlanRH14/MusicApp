package com.example.musicapp.presentation.playlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.presentation.playlist.mvi.PlaylistEffect
import com.example.musicapp.presentation.playlist.mvi.PlaylistState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PlaylistViewModel : ViewModel() {

    private val _state = MutableStateFlow(PlaylistState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<PlaylistEffect>()
    val effect = _effect.asSharedFlow()

    fun onEvent(event: PlaylistUIEvent) {

    }

    private fun showError() {
        viewModelScope.launch {
            _effect.emit(PlaylistEffect.ShowMessageError("Error"))
        }
    }
}