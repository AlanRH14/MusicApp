package com.example.musicapp.data.mapper_impl

import com.example.musicapp.common.ApiMapper
import com.example.musicapp.data.model.PlaylistModelDto
import com.example.musicapp.domain.model.Playlist

class PlaylistApiMapperImpl: ApiMapper<List<PlaylistModelDto>, List<Playlist>> {

    override fun mapToDomain(apiDto: List<PlaylistModelDto>): List<Playlist> {
        TODO("Not yet implemented")
    }
}