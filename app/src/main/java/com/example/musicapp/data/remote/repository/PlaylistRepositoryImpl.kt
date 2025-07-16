package com.example.musicapp.data.remote.repository

import com.example.musicapp.data.remote.api.ApiService
import com.example.musicapp.domain.model.Playlist
import com.example.musicapp.domain.repository.PlaylistRepository

class PlaylistRepositoryImpl(
    private val apiService: ApiService
) : PlaylistRepository {

    override suspend fun getPlaylist(): List<Playlist> {
        TODO("Not yet implemented")
    }
}