package com.example.musicapp.data.mapper_impl.home

import com.example.musicapp.common.music.ApiMapper
import com.example.musicapp.data.model.reponse.HomeResponse
import com.example.musicapp.domain.model.Home

class HomeApiMapperImpl : ApiMapper<HomeResponse, Home> {

    override fun mapToDomain(apiDto: HomeResponse): Home {
        return Home(
            continueListening = apiDto.continueListening ?: emptyList(),
            recommendedSong = emptyList(),
            topMixes = emptyList(),
        )
    }
}