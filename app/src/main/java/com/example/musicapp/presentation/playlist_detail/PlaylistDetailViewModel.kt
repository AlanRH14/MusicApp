package com.example.musicapp.presentation.playlist_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.common.Resource
import com.example.musicapp.domain.repository.PlaylistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlaylistDetailViewModel(
    private val repository: PlaylistRepository
) : ViewModel() {

    private val _state = MutableStateFlow(PlaylistDetailState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<PlaylistDetailEffect>()
    val effect = _effect.asSharedFlow()

    fun onEvent(event: PlaylistDetailUIEvent) {}

    private fun getPlaylistDetails(playlistID: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPlaylistDetails(playlistID = playlistID).collect { details ->
                when (details) {
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
                                error = details.message
                            )
                        }
                    }
                }
            }
        }
    }
}