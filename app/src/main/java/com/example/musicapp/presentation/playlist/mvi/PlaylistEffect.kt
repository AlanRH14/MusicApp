package com.example.musicapp.presentation.playlist.mvi

interface PlaylistEffect {
    data class ShowMessageError(val message: String) : PlaylistEffect
    data object NavigateToCreatePlaylist: PlaylistEffect
}