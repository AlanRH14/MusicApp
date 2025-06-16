package com.example.musicapp.domain.model

import com.example.musicapp.data.model.AlbumDto
import com.example.musicapp.data.model.ContinueListeningDto
import com.example.musicapp.data.model.SongDto

data class Home(
    val continueListening: List<ContinueListeningDto> = emptyList(),
    val recommendedSong: List<SongDto> = emptyList(),
    val topMixes: List<AlbumDto> = emptyList(),
)
