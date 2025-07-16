package com.example.musicapp.domain.model

data class Playlist(
    val id: String,
    val name: String,
    val description: String,
    val coverImage: String,
    val userId: String,
    val songs: List<Song>,
    val createdAt: Long,
    val updateAt: Long
)
