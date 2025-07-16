package com.example.musicapp.domain.repository

import com.example.musicapp.domain.model.Playlist

interface PlaylistRepository {
    suspend fun getPlaylist(): List<Playlist>
}