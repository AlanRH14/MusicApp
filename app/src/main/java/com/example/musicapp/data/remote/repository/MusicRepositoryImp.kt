package com.example.musicapp.data.remote.repository

import com.example.musicapp.common.ApiMapper
import com.example.musicapp.common.Resource
import com.example.musicapp.data.model.SongDto
import com.example.musicapp.data.remote.api.ApiService
import com.example.musicapp.domain.model.Song
import com.example.musicapp.domain.repository.MusicRepository

class MusicRepositoryImp(
    private val apiService: ApiService,
    private val apiSongMapper: ApiMapper<SongDto, Song>
) : MusicRepository {

    override suspend fun getSongById(): Resource<Song> {
        Resource.Loading

        return try {
            val response = apiService.getSongByID("")
            if (response.isSuccessful) {
                response.body()?.let { song ->
                    Resource.Success(apiSongMapper.mapToDomain(apiDto = song))
                } ?: Resource.Success(Song())
            } else {
                throw Exception("Get song by ID failed")
            }
        } catch (e: Exception) {
            Resource.Error(message = "Error: ${e.message}")
        }
    }
}