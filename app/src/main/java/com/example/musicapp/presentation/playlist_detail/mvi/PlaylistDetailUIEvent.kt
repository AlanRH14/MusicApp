package com.example.musicapp.presentation.playlist_detail.mvi

sealed interface PlaylistDetailUIEvent {
    data class OnGetPlaylistDetail(val playlistID: String) : PlaylistDetailUIEvent
    data class OnDeleteSongOfPlaylist(
        val playlistID: String,
        val songID: String
    ) : PlaylistDetailUIEvent
}