package com.example.musicapp.data.mapper_impl.song

import com.example.musicapp.common.ApiMapper
import com.example.musicapp.data.model.AlbumDto
import com.example.musicapp.domain.model.Album

class AlbumApiMapper: ApiMapper<AlbumDto, Album> {

    override fun mapToDomain(apiDto: AlbumDto): Album {
        return Album(
            artist = ,
            coverImage = apiDto.coverImage ?: "",
        )
    }
}