package com.example.musicapp.data.remote.repository

import com.example.musicapp.common.ApiMapper
import com.example.musicapp.common.Resource
import com.example.musicapp.data.local.datasource.UserLocalDataSource
import com.example.musicapp.data.model.PlaylistDto
import com.example.musicapp.data.model.request.CreatePlaylistRequest
import com.example.musicapp.data.remote.api.ApiService
import com.example.musicapp.domain.model.Playlist
import com.example.musicapp.domain.repository.PlaylistRepository

class PlaylistRepositoryImpl(
    private val apiService: ApiService,
    private val apiPlaylistMapper: ApiMapper<List<PlaylistDto>, List<Playlist>>,
    private val userLocalDataSource: UserLocalDataSource
) : PlaylistRepository {

    override suspend fun getPlaylist(): Resource<List<Playlist>> {
        Resource.Loading

        return try {
            userLocalDataSource.getUser()?.let { userData ->
                val response = apiService.getPlaylist(token = "Bearer ${userData.token}")
                if (response.isSuccessful) {
                    response.body()?.let { res ->
                        Resource.Success(data = apiPlaylistMapper.mapToDomain(apiDto = res))
                    } ?: Resource.Success(data = emptyList())
                } else {
                    throw Exception(response.message())
                }
            } ?: throw Exception("Get local user error")
        } catch (e: Exception) {
            Resource.Error(message = "Error: ${e.message}")
        }
    }

    override suspend fun createPlaylist(playlistRequest: CreatePlaylistRequest): Resource<List<Playlist>> {
        Resource.Loading

        return try {
            val response = apiService.createPlaylist(playlistRequest = playlistRequest)
            if (response.isSuccessful) {
                response.body()?.let { res ->
                    Resource.Success(data = apiPlaylistMapper.mapToDomain(apiDto = res))
                } ?: Resource.Success(data = emptyList())
            } else {
                throw Exception(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(message = "Error: ${e.message}")
        }
    }
}