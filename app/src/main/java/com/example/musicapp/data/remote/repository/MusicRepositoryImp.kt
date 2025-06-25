package com.example.musicapp.data.remote.repository

import com.example.musicapp.common.Resource
import com.example.musicapp.data.remote.api.ApiService
import com.example.musicapp.domain.model.Song
import com.example.musicapp.domain.repository.MusicRepository

class MusicRepositoryImp(
    private val apiService: ApiService,
): MusicRepository {

    override suspend fun getSongById(): Resource<Song> {
        Resource.Loading

        return try {
            val response = apiService.getSongByID("")
            if (response.isSuccessful) {
                response.body()?.let {

                } ?: Resource.Success(Song())
            } else {
                rE
            }
        } catch (e: Exception)
    }
}