package com.example.musicapp.data.model

data class PlaylistModelDto(
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val coverImage: String? = null,
    val userId: String? = null,
    val songs: List<SongDto>? = null,
    val createdAt: Long? = null,
    val updateAt: Long? = null
)
