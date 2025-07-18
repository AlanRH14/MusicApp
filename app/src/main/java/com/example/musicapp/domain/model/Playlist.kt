package com.example.musicapp.domain.model

data class Playlist(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val coverImage: String = "",
    val userId: String = "",
    val songs: List<Song> = emptyList(),
    val createdAt: Long = 0L,
    val updateAt: Long = 0L
)
