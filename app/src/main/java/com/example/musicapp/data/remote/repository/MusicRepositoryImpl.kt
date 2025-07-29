package com.example.musicapp.data.remote.repository

import com.example.musicapp.common.ApiMapper
import com.example.musicapp.common.Resource
import com.example.musicapp.data.local.datasource.UserLocalDataSource
import com.example.musicapp.data.model.SongDto
import com.example.musicapp.data.remote.api.ApiService
import com.example.musicapp.domain.model.Song
import com.example.musicapp.domain.repository.MusicRepository
import com.example.musicapp.utils.Constants.AUTHENTICATION_HEADER_TYPE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MusicRepositoryImpl(
    private val apiService: ApiService,
    private val apiSongMapper: ApiMapper<SongDto, Song>,
    private val userLocalDataSource: UserLocalDataSource,
) : MusicRepository {

    override fun getSongById(id: String): Flow<Resource<Song>> = flow {
        emit(Resource.Loading)
        userLocalDataSource.getUser()?.let { userData ->
            val response = apiService.getSongByID(
                token = "$AUTHENTICATION_HEADER_TYPE ${userData.token}",
                id = id
            )
            if (response.isSuccessful) {
                response.body()?.let { res ->
                    emit(Resource.Success(apiSongMapper.mapToDomain(apiDto = res)))
                } ?: emit(Resource.Success(Song()))
            } else {
                throw Exception(response.message())
            }
        } ?: throw Exception("Get local user error")
    }.catch { error ->
        emit(Resource.Error(message = "Error: ${error.message}"))
    }
}