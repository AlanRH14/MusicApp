package com.example.musicapp.presentation.create_playlist.mvi

sealed interface CreatePlaylistEffect {
    data object NavigationToPlaylist: CreatePlaylistEffect
    data object NavigationToBack: CreatePlaylistEffect
}