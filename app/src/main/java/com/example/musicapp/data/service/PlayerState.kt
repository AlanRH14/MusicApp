package com.example.musicapp.data.service

import com.example.musicapp.domain.model.Song

data class PlayerState(
    val isPlaying: Boolean = false,
    val currentSong: Song? = null,
    val currentPosition: Long = 0L,
    val duration: Long = 0L,
    val isBuffering: Boolean = false,
    val error: String? = null
)
