package com.example.musicapp.data.remote.repository

import com.example.musicapp.common.Mapper
import com.example.musicapp.common.Resource
import com.example.musicapp.data.local.preferences.ConstantsPreferences
import com.example.musicapp.data.model.SongDto
import com.example.musicapp.data.remote.api.HomeApiService
import com.example.musicapp.data.remote.api.utils.safeFlowApiCall
import com.example.musicapp.domain.exception.AuthException
import com.example.musicapp.domain.model.Song
import com.example.musicapp.domain.repository.DataStoreHandle
import com.example.musicapp.domain.repository.MusicRepository
import com.example.musicapp.utils.constants.AppConstants.AUTHENTICATION_HEADER_TYPE
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class MusicRepositoryImpl(
    private val homeApiService: HomeApiService,
    private val dataStore: DataStoreHandle,
    private val apiSongMapper: Mapper<SongDto, Song>,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MusicRepository {

    override fun getSongById(id: String): Flow<Resource<Song>> =
        safeFlowApiCall(
            ioDispatcher = ioDispatcher,
            apiCall = {
                val token = dataStore.readState(ConstantsPreferences.AuthTokenPreferences).first()
                if (token.isEmpty()) {
                    throw AuthException.UserNotFoundException()
                }
                homeApiService.getSongByID(id = id)
            },
            transform = { res -> apiSongMapper.mapTo(input = res) }
        )
}
