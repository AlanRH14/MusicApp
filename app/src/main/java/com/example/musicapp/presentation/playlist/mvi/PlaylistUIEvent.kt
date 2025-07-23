package com.example.musicapp.presentation.playlist.mvi

interface PlaylistUIEvent {
    data object GetPlaylist: PlaylistUIEvent
    data object OnClickedRetry : PlaylistUIEvent
    data object NavigateToCreatePlaylist: PlaylistUIEvent
}