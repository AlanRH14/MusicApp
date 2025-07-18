package com.example.musicapp.presentation.create_playlist.mvi

import com.example.musicapp.domain.model.Playlist

data class CreatePlaylistState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val name: String = "",
    val description: String = "",
    val playlist: List<Playlist> = emptyList()
)
