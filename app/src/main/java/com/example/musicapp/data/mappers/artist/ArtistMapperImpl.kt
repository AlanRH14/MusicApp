package com.example.musicapp.data.mappers.artist

import com.example.musicapp.common.Mapper
import com.example.musicapp.data.model.ArtistDto
import com.example.musicapp.domain.model.Artist

class ArtistMapperImpl : Mapper<ArtistDto, Artist> {

    override fun mapTo(apiDto: ArtistDto): Artist {
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
