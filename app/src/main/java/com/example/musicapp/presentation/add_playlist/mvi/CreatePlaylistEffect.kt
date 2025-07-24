package com.example.musicapp.presentation.add_playlist.mvi

sealed interface CreatePlaylistEffect {
    data object NavigateToPlaylist: CreatePlaylistEffect
    data object PreviousScreen: CreatePlaylistEffect
}