package com.example.musicapp.presentation.playlist.mvi

sealed interface PlaylistEffect {
    data class ShowMessageError(val message: String) : PlaylistEffect
    data object NavigateToCreatePlaylist: PlaylistEffect
    data class NavigateToDetailPlaylist(val playlistID: String): PlaylistEffect
}