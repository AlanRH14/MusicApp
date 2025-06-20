package com.example.musicapp.data.mapper_impl.home

import com.example.musicapp.common.ApiMapper
import com.example.musicapp.data.model.SongDto
import com.example.musicapp.data.model.reponse.HomeResponse
import com.example.musicapp.domain.model.Home
import com.example.musicapp.domain.model.Song

class HomeApiMapperImpl(
    private val apiSongMapper: ApiMapper<SongDto, Song>
) : ApiMapper<HomeResponse, Home> {

    override fun mapToDomain(apiDto: HomeResponse): Home {
        return Home(
            continueListening = apiDto.continueListening?.map { continueListening ->
                apiSongMapper.mapToDomain(
                    continueListening
                )
            } ?: emptyList(),
            recommendedSong = emptyList(),
            topMixes = emptyList(),
        )
    }
}