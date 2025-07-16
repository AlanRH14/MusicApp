package com.example.musicapp.domain.repository

import com.example.musicapp.data.model.PlaylistModelDto

interface PlaylistRepository {
    suspend fun getPlaylist(): List<PlaylistModelDto>
}