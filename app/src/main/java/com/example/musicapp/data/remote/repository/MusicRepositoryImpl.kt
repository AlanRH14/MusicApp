package com.example.musicapp.data.remote.repository

import com.example.musicapp.common.Mapper
import com.example.musicapp.common.Resource
import com.example.musicapp.data.local.preferences.ConstantsPreferences
import com.example.musicapp.data.model.SongDto
import com.example.musicapp.data.remote.api.HomeApiService
import com.example.musicapp.domain.exception.AuthException
import com.example.musicapp.domain.model.Song
import com.example.musicapp.domain.repository.DataStoreHandle
import com.example.musicapp.domain.repository.MusicRepository
import com.example.musicapp.utils.constants.Constants.AUTHENTICATION_HEADER_TYPE
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MusicRepositoryImpl(
    private val homeApiService: HomeApiService,
    private val dataStore: DataStoreHandle,
    private val apiSongMapper: Mapper<SongDto, Song>,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MusicRepository {

    override fun getSongById(id: String): Flow<Resource<Song>> = flow {
        emit(Resource.Loading)

        val token = dataStore.readState(ConstantsPreferences.AuthTokenPreferences).first()
        if (token.isEmpty()) {
            throw AuthException.UserNotFoundException()
        }

        val response = homeApiService.getSongByID(
            token = "$AUTHENTICATION_HEADER_TYPE $token",
            id = id
        )

        if (response.isSuccessful) {
            val songData = response.body()?.let { res ->
                apiSongMapper.mapTo(input = res)
            } ?: Song()
            emit(Resource.Success(data = songData))
        } else {
            val errorBody = response.errorBody()?.string()
            val errorMessage = errorBody.takeIf { !it.isNullOrEmpty() } ?: response.message()
            throw Exception(errorMessage)
        }
    }.catch { error ->
        emit(Resource.Error(throwable = error))
    }.flowOn(ioDispatcher)
}
