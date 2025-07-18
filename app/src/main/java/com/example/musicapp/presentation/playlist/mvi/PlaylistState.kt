package com.example.musicapp.presentation.playlist.mvi

import com.example.musicapp.domain.model.Playlist

data class PlaylistState(
    val error: String? = null,
    val isLoading: Boolean = true,
    val playlist: List<Playlist> = emptyList()
)
