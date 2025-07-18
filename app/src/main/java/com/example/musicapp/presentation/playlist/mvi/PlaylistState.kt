package com.example.musicapp.presentation.playlist.mvi

data class PlaylistState(
    val error: String? = null,
    val isLoading: Boolean = true,

)
