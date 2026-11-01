package com.example.musicapp.data.mappers.artist

import com.example.musicapp.common.Mapper
import com.example.musicapp.data.model.ArtistDto
import com.example.musicapp.domain.model.Artist

class ArtistMapperImpl : Mapper<ArtistDto, Artist> {

    override fun mapTo(input: ArtistDto): Artist {
        return Artist(
            bio = input.bio ?: "",
            createdAt = input.createdAt ?: 0L,
            id = input.id ?: "",
            name = input.name ?: "",
            profilePicture = input.profilePicture ?: "",
            updatedAt = input.updatedAt ?: 0L,
        )
    }
}
