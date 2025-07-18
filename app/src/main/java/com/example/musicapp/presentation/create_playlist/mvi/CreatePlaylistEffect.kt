package com.example.musicapp.presentation.create_playlist.mvi

import com.example.musicapp.domain.model.Playlist

sealed interface CreatePlaylistEffect {
    data class ShowErrorMessage(val message: String) : CreatePlaylistEffect
    data class NavigateToPlaylist(val playlist: List<Playlist>): CreatePlaylistEffect
}