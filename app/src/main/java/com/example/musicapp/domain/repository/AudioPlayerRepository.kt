package com.example.musicapp.domain.repository

import com.example.musicapp.domain.model.AudioStatus
import kotlinx.coroutines.flow.StateFlow

interface AudioPlayerRepository {
    val audioStatus: StateFlow<AudioStatus>
    fun playSong(songID: String)
    fun togglePlayPause()
    fun seekTo(position: Long)
    fun unbindService()
}