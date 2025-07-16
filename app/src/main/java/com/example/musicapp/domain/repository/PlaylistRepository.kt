package com.example.musicapp.domain.repository

interface PlaylistRepository {
    suspend fun getPlaylist(): List<PlaylistModelDto>
}