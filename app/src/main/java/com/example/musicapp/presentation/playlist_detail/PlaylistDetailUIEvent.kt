package com.example.musicapp.presentation.playlist_detail

sealed interface PlaylistDetailUIEvent {
    data class OnDeleteSongOfPlaylist(
        val playlistID: String,
        val songID: String
    ) : PlaylistDetailUIEvent
}