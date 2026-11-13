package com.example.musicapp.domain.model

data class AudioStatus(
    val isPlaying: Boolean = false,
    val currentSong: Song? = null,
    val currentPosition: Long = 0L,
    val duration: Long = 0L,
    val isBuffering: Boolean = false,
    val error: String? = null
)
