package com.example.musicapp.domain.repository

import com.example.musicapp.common.Resource
import com.example.musicapp.data.model.response.UpdatePlaylistSongResponse

import com.example.musicapp.domain.model.CreatePlaylist
import com.example.musicapp.domain.model.Playlist
import kotlinx.coroutines.flow.Flow

interface PlaylistRepository {
    fun getPlaylist(): Flow<Resource<List<Playlist>>>

    suspend fun createPlaylist(playlistData: CreatePlaylist): Resource<Playlist>

    suspend fun addSongToPlaylist(
        playlistID: String,
        songID: String
    ): Resource<UpdatePlaylistSongResponse>

    suspend fun deleteSongFromPlaylist(
        playlistID: String,
        songID: String,
    ): Resource<UpdatePlaylistSongResponse>

    fun getPlaylistDetails(playlistID: String): Flow<Resource<Playlist>>
}
