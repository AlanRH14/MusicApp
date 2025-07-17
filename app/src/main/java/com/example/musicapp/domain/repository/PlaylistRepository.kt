package com.example.musicapp.domain.repository

import com.example.musicapp.common.Resource
import com.example.musicapp.data.model.request.CreatePlaylistRequest
import com.example.musicapp.domain.model.Playlist
import kotlinx.coroutines.flow.Flow

interface PlaylistRepository {
    suspend fun getPlaylist(): Flow<Resource<List<Playlist>>>

    suspend fun createPlaylist(playlistRequest: CreatePlaylistRequest): Resource<List<Playlist>>
}