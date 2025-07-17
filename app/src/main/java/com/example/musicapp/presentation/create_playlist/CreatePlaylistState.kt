package com.example.musicapp.presentation.create_playlist

data class CreatePlaylistState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val name: String = "",
    val description: String = ""
)
