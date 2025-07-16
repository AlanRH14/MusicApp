package com.example.musicapp.data.remote.repository

import com.example.musicapp.common.ApiMapper
import com.example.musicapp.common.Resource
import com.example.musicapp.data.model.PlaylistDto
import com.example.musicapp.data.remote.api.ApiService
import com.example.musicapp.domain.model.Playlist
import com.example.musicapp.domain.repository.PlaylistRepository

class PlaylistRepositoryImpl(
    private val apiService: ApiService,
    private val apiPlaylistMapper: ApiMapper<List<PlaylistDto>, List<Playlist>>
) : PlaylistRepository {

    override suspend fun getPlaylist(): Resource<List<Playlist>> {
        Resource.Loading

        return try {
            val response = apiService.getPlaylist()
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