package com.example.musicapp.presentation.play_song.mvi

import com.example.musicapp.domain.model.AudioStatus
import com.example.musicapp.domain.model.Playlist
import com.example.musicapp.domain.model.Song

data class PlaySongState(
    val isLoading: Boolean = false,
    val audioStatus: AudioStatus = AudioStatus(),
    val song: Song? = null,
    val playlists: List<Playlist> = emptyList(),
    val shouldShowSheet: Boolean = false,
    val error: String? = null
)
