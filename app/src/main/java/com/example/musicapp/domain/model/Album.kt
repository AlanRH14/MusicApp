package com.example.musicapp.domain.model

data class Album(
    val artist: Artist,
    val coverImage: String,
    val genre: String,
    val id: String,
    val releaseDate: Long,
    val song: List<Song>,
    val title: String
)
