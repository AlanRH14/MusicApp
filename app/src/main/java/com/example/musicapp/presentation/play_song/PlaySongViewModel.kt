package com.example.musicapp.presentation.play_song

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.common.Resource
import com.example.musicapp.data.service.AudioPlayerManager
import com.example.musicapp.domain.repository.PlaylistRepository
import com.example.musicapp.presentation.play_song.mvi.PlaySongEffect
import com.example.musicapp.presentation.play_song.mvi.PlaySongState
import com.example.musicapp.presentation.play_song.mvi.PlaySongUIEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlaySongViewModel(
    private val audioPlayerManager: AudioPlayerManager,
    private val playlistRepository: PlaylistRepository
) : ViewModel() {

    private val _state = MutableStateFlow(PlaySongState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<PlaySongEffect>()
    val effect = _effect.asSharedFlow()


    init {
        observePlayerState()
    }

    fun onEvent(event: PlaySongUIEvent) {
        when (event) {
            is PlaySongUIEvent.GetSongByID -> audioPlayerManager.playSong(songID = event.songID)
            is PlaySongUIEvent.OnToggleToPause -> audioPlayerManager.toggleToPause()
            is PlaySongUIEvent.OnSeekTo -> audioPlayerManager.seekTo(event.position)
            is PlaySongUIEvent.OnAddPlaylistClicked -> getPlaylist()
            is PlaySongUIEvent.OnToggleToBottomSheet -> onToggleToBottomSheet()
            is PlaySongUIEvent.OnAddSongToPlaylist -> addSongToPlaylist(
                playlistID = event.playlistID,
                songID = event.songID
            )
        }
    }

    private fun observePlayerState() {
        viewModelScope.launch {
            audioPlayerManager.playerState.collect { player ->
                _state.update {
                    it.copy(
                        isPlaying = player.isPlaying,
                        currentSong = player.currentSong ?: it.currentSong,
                        currentPosition = player.currentPosition,
                        duration = player.duration,
                        isBuffering = player.isBuffering,
                        error = player.error ?: it.error
                    )
                }
            }
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
                        _state.update {
                            it.copy(
                                isLoading = false,
                                shouldShowSheet = true,
                                playlists = playlist.data
                            )
                        }
                    }

                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = playlist.throwable?.message
                            )
                        }
                    }
                }
            }
        }
    }

    private fun addSongToPlaylist(playlistID: String, songID: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val response = playlistRepository.addSongToPlaylist(
                playlistID = playlistID,
                songID = songID
            )

            when (response) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            shouldShowSheet = false
                        )
                    }
                    _effect.emit(PlaySongEffect.ShowMessage(response.data.message))
                }

                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            shouldShowSheet = false,
                            error = response.throwable?.message
                        )
                    }
                }

                is Resource.Loading -> {
                    _state.update { it.copy(isLoading = false) }
                }
            }
        }
    }

    private fun onToggleToBottomSheet() {
        _state.update { it.copy(shouldShowSheet = !it.shouldShowSheet) }
    }

    override fun onCleared() {
        audioPlayerManager.unbind()
    }
}
