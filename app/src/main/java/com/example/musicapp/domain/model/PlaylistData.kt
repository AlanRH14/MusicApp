package com.example.musicapp.domain.model

data class PlaylistData(
    val name: String? = null,
    val description: String? = null,
    val coverImage: String? = null,
    val songIds: List<String>? = null
)
