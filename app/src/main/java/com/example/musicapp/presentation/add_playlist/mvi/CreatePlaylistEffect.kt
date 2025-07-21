package com.example.musicapp.presentation.add_playlist.mvi

sealed interface CreatePlaylistEffect {
    data class ShowErrorMessage(val message: String) : CreatePlaylistEffect
    data object NavigateToPlaylist: CreatePlaylistEffect
}