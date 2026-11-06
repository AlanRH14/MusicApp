package com.example.musicapp.data.remote.repository

import com.example.musicapp.common.Mapper
import com.example.musicapp.common.Resource
import com.example.musicapp.data.model.SongDto
import com.example.musicapp.data.remote.api.HomeApiService
import com.example.musicapp.data.remote.api.utils.safeFlowApiCall
import com.example.musicapp.domain.model.Song
import com.example.musicapp.domain.repository.MusicRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class MusicRepositoryImpl(
    private val homeApiService: HomeApiService,
    private val apiSongMapper: Mapper<SongDto, Song>,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MusicRepository {

    override fun getSongById(id: String): Flow<Resource<Song>> =
        safeFlowApiCall(
            ioDispatcher = ioDispatcher,
            apiCall = { homeApiService.getSongByID(id = id) },
            transform = { res -> apiSongMapper.mapTo(input = res) }
        )
}
