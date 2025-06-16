package com.example.musicapp.domain.model

import com.example.musicapp.data.model.ArtistDto
import com.example.musicapp.data.model.SongDto

data class Album(
    val artist: ArtistDto,
    val coverImage: String,
    val genre: String,
    val id: String,
    val releaseDate: Long,
    val song: List<SongDto>,
    val title: String
)
