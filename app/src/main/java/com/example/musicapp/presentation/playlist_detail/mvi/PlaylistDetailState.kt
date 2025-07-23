package com.example.musicapp.presentation.playlist_detail.mvi

import com.example.musicapp.domain.model.Playlist

data class PlaylistDetailState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val playlist: Playlist? = null
)
