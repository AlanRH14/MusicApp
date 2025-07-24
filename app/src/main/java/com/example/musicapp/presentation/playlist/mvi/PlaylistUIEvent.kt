package com.example.musicapp.presentation.playlist.mvi

sealed interface PlaylistUIEvent {
    data object GetPlaylist : PlaylistUIEvent
    data object OnClickedRetry : PlaylistUIEvent
    data object OnCreatePlaylistClicked : PlaylistUIEvent
    data class NavigateToPlaylistDetail(val playlistID: String) : PlaylistUIEvent
}