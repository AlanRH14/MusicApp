package com.example.musicapp.presentation.playlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.common.Resource
import com.example.musicapp.domain.repository.PlaylistRepository
import com.example.musicapp.presentation.playlist.mvi.PlaylistEffect
import com.example.musicapp.presentation.playlist.mvi.PlaylistState
import com.example.musicapp.presentation.playlist.mvi.PlaylistUIEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlaylistViewModel(
    private val playlistRepository: PlaylistRepository
) : ViewModel() {

    private val _state = MutableStateFlow(PlaylistState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<PlaylistEffect>()
    val effect = _effect.asSharedFlow()

    fun onEvent(event: PlaylistUIEvent) {
        when (event) {
            is PlaylistUIEvent.GetPlaylist -> getPlaylist()
            is PlaylistUIEvent.OnClickedRetry -> getPlaylist()
        }
    }

    private fun getPlaylist() {
        viewModelScope.launch {
            playlistRepository.getPlaylist().collect { playlist ->
                when (playlist) {
                    is Resource.Loading -> {
                        _state.update { it.copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        _state.update { it.copy(isLoading = false) }
                    }

                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = playlist.message
                            )
                        }
                    }
                }
            }
        }
    }
}