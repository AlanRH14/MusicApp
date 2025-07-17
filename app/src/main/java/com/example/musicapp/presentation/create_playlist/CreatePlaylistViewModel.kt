package com.example.musicapp.presentation.create_playlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.common.Resource
import com.example.musicapp.data.model.request.CreatePlaylistRequest
import com.example.musicapp.domain.repository.PlaylistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CreatePlaylistViewModel(
    private val playlistRepository: PlaylistRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CreatePlaylistState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<CreatePlaylistEffect>()
    val effect = _effect.asSharedFlow()

    fun onEvent(event: CreatePlaylistUIEvent) {
        when (event) {
            is CreatePlaylistUIEvent.OnCreatePlaylistClicked -> onAddPlaylist()
        }
    }

    private fun onAddPlaylist() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = playlistRepository.createPlaylist(
                CreatePlaylistRequest(
                    name = _state.value.name,
                    description = _state.value.description
                )
            )

            when (response) {
                is Resource.Loading -> {
                    _state.update { it.copy(isLoading = true) }
                }

                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            playlist = response.data
                        )
                    }
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
}