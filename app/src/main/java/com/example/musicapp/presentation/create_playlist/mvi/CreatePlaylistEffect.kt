package com.example.musicapp.presentation.create_playlist.mvi

sealed interface CreatePlaylistEffect {
    data object NavigateToPlaylist: CreatePlaylistEffect
    data object NavigateToBack: CreatePlaylistEffect
}