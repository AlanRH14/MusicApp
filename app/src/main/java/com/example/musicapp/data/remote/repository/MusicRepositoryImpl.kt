package com.example.musicapp.data.remote.repository

import com.example.musicapp.common.Mapper
import com.example.musicapp.common.Resource
import com.example.musicapp.data.local.datasource.UserLocalDataSource
import com.example.musicapp.data.model.SongDto
import com.example.musicapp.data.remote.api.HomeApiService
import com.example.musicapp.domain.exception.AuthException
import com.example.musicapp.domain.model.Song
import com.example.musicapp.domain.repository.MusicRepository
import com.example.musicapp.utils.Constants.AUTHENTICATION_HEADER_TYPE
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MusicRepositoryImpl(
    private val homeApiService: HomeApiService,
    private val apiSongMapper: Mapper<SongDto, Song>,
    private val userLocalDataSource: UserLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MusicRepository {

    override fun getSongById(id: String): Flow<Resource<Song>> = flow {
        emit(Resource.Loading)

        val userData = userLocalDataSource.getUser()
            ?: throw AuthException.UserNotFoundException()

        val response = homeApiService.getSongByID(
            token = "$AUTHENTICATION_HEADER_TYPE ${userData.token}",
            id = id
        )

        if (response.isSuccessful) {
            val songData = response.body()?.let { res ->
                apiSongMapper.mapTo(apiDto = res)
            } ?: Song()
            emit(Resource.Success(data = songData))
        } else {
            throw Exception(response.message())
        }
    }.catch { error ->
        emit(Resource.Error(throwable = error))
    }.flowOn(ioDispatcher)
}
