package com.example.musicapp.presentation.playlist_detail

sealed interface PlaylistDetailUIEvent {
    data class OnDeleteSongOfPlaylist(val songID: String) : PlaylistDetailUIEvent
}