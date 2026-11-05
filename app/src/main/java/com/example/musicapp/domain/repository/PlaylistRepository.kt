package com.example.musicapp.domain.repository

import com.example.musicapp.common.Resource
import com.example.musicapp.domain.model.ActionMessage
import com.example.musicapp.domain.model.PlaylistData
import com.example.musicapp.domain.model.Playlist
import kotlinx.coroutines.flow.Flow

interface PlaylistRepository {
    fun getPlaylist(): Flow<Resource<List<Playlist>>>

    suspend fun createPlaylist(playlistData: PlaylistData): Resource<Playlist>

    suspend fun addSongToPlaylist(
        playlistID: String,
        songID: String
    ): Resource<ActionMessage>

    suspend fun deleteSongFromPlaylist(
        playlistID: String,
        songID: String,
    ): Resource<ActionMessage>

    fun getPlaylistDetails(playlistID: String): Flow<Resource<Playlist>>
}
