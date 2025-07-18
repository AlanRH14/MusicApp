package com.example.musicapp.presentation.create_playlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.common.Resource
import com.example.musicapp.data.model.request.CreatePlaylistRequest
import com.example.musicapp.domain.repository.PlaylistRepository
import com.example.musicapp.presentation.create_playlist.mvi.CreatePlaylistEffect
import com.example.musicapp.presentation.create_playlist.mvi.CreatePlaylistState
import com.example.musicapp.presentation.create_playlist.mvi.CreatePlaylistUIEvent
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
            is CreatePlaylistUIEvent.OnNameUpdate -> onNameUpdate(event.name)
            is CreatePlaylistUIEvent.OnDescriptionUpdate -> onDescriptionUpdate(event.description)
        }
    }

    private fun onNameUpdate(name: String) {
        _state.update { it.copy(name = name) }
    }

    private fun onDescriptionUpdate(description: String) {
        _state.update { it.copy(description = description) }
    }

    private fun onAddPlaylist() {
        if (validateEmptyInputs()) return

        viewModelScope.launch(Dispatchers.IO) {
            val response = playlistRepository.createPlaylist(
                CreatePlaylistRequest(
                    name = _state.value.name,
                    description = _state.value.description,
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

    private fun validateEmptyInputs(): Boolean {
        val isNameEmpty = _state.value.name.isEmpty()
        val isDescriptionEmpty = _state.value.description.isEmpty()

        _state.update {
            it.copy(
                isNameEmpty = isNameEmpty,
                isDescriptionEmpty = isDescriptionEmpty
            )
        }

        return isNameEmpty || isDescriptionEmpty
    }
}