package com.example.musicapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.domain.repository.HomeRepository
import com.example.musicapp.common.Resource
import com.example.musicapp.presentation.home.mvi.HomeEffect
import com.example.musicapp.presentation.home.mvi.HomeState
import com.example.musicapp.presentation.home.mvi.HomeUIEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: HomeRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<HomeEffect>()
    val effect = _effect.asSharedFlow()

    fun onEvent(event: HomeUIEvent) {
        when (event) {
            is HomeUIEvent.GetHomeData -> getHome()
            is HomeUIEvent.OnSongClicked -> navigateToPlaySong(songID = event.songID)
            is HomeUIEvent.OnAlbumClicked -> showMessageTest("OnAlbumClicked")
            is HomeUIEvent.OnSongRecommendationClicked -> showMessageTest("OnSongRecommendationClicked")
            is HomeUIEvent.OnPlaylistClicked -> navigateToPlaylist()
        }
    }

    private fun getHome() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getHomeData().collect { data ->
                when (data) {
                    is Resource.Loading -> {
                        _state.update { it.copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                homData = data.data,
                                isLoading = false
                            )
                        }
                    }

                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                error = data.message,
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }

    private fun showMessageTest(eventMessage: String) {
        viewModelScope.launch {
            _effect.emit(HomeEffect.ShowMessage(message = eventMessage))
        }
    }

    private fun navigateToPlaySong(songID: String) {
        viewModelScope.launch {
            _effect.emit(HomeEffect.NavigateToPlaySong(songID = songID))
        }
    }

    private fun navigateToPlaylist() {
        viewModelScope.launch {
            _effect.emit(HomeEffect.NavigationToPlaylist)
        }
    }
}