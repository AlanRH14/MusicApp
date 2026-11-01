package com.example.musicapp.data.mappers.song

import com.example.musicapp.common.Mapper
import com.example.musicapp.data.model.ArtistDto
import com.example.musicapp.data.model.SongDto
import com.example.musicapp.domain.model.Artist
import com.example.musicapp.domain.model.Song

class SongMapperImpl(
    private val apiArtistMapper: Mapper<ArtistDto, Artist>
) : Mapper<SongDto, Song> {

    override fun mapTo(apiDto: SongDto): Song {
        return Song(
            artist = apiArtistMapper.mapTo(apiDto = apiDto.artist ?: ArtistDto()),
            audioUrl = apiDto.audioUrl ?: "",
            coverImage = apiDto.coverImage ?: "",
            createdAt = apiDto.createdAt ?: 0L,
            duration = apiDto.duration ?: 0L,
            genre = apiDto.genre ?: "",
            id = apiDto.id ?: "",
            releaseDate = apiDto.releaseDate ?: 0L,
            title = apiDto.title ?: "",
            updateAt = apiDto.updateAt ?: 0L,
        )
    }
}
