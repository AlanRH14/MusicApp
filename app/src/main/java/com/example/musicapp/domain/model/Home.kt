package com.example.musicapp.domain.model

data class Home(
    val continueListening: List<ContinueListening> = emptyList(),
    val recommendedSong: List<Song> = emptyList(),
    val topMixes: List<Album> = emptyList(),
)
