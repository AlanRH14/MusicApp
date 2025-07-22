package com.example.musicapp.presentation.play_song.mvi

import com.example.musicapp.domain.model.Playlist
import com.example.musicapp.presentation.playlist.mvi.PlaylistEffect

sealed interface PlaySongEffect {
    data class ShowErrorMessage(val message: String): PlaySongEffect
    data class NavigateToPlaylist(val idSong: String): PlaySongEffect
    data class ShowPlaylistSelection(val playlists: List<Playlist>): PlaylistEffect
}