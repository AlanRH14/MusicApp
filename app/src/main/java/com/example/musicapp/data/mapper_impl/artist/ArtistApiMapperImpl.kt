package com.example.musicapp.data.mapper_impl.artist

import com.example.musicapp.common.music.ApiMapper
import com.example.musicapp.data.model.ArtistDto
import com.example.musicapp.domain.model.Artist

class ArtistApiMapperImpl : ApiMapper<ArtistDto, Artist> {

    override fun mapToDomain(apiDto: ArtistDto): Artist {
        return Artist(
            bio = apiDto.bio ?: "",
            createdAt = apiDto.createdAt ?: 0L,
            id = apiDto.id ?: "",
            name = apiDto.name ?: "",
            profilePicture = apiDto.profilePicture ?: "",
            updatedAt = apiDto.updatedAt ?: 0L,
        )
    }
}