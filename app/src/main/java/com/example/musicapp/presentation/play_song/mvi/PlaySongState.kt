package com.example.musicapp.presentation.play_song.mvi

import com.example.musicapp.domain.model.Playlist
import com.example.musicapp.domain.model.Song

data class PlaySongState(
    val isLoading: Boolean = false,
    val isPlaying: Boolean = false,
    val currentSong: Song? = null,
    val currentPosition: Long = 0L,
    val duration: Long = 0L,
    val isBuffering: Boolean = false,
    val song: Song? = null,
    val playlists: List<Playlist> = emptyList(),
    val shouldShowSheet: Boolean = false,
    val error: String? = null
)
