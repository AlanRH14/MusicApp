package com.example.musicapp.data.mapper_impl.song

import com.example.musicapp.common.music.ApiMapper
import com.example.musicapp.data.model.ArtistDto
import com.example.musicapp.data.model.SongDto
import com.example.musicapp.domain.model.Artist
import com.example.musicapp.domain.model.Song

class SongApiMapperImpl(
    private val apiArtistMapper: ApiMapper<ArtistDto, Artist>
) : ApiMapper<SongDto, Song> {

    override fun mapToDomain(apiDto: SongDto): Song {
        return Song(
            artist = apiArtistMapper.mapToDomain(apiDto = apiDto.artist ?: ArtistDto()),
            audioUrl = apiDto.audioUrl ?: "",
            coverImage = apiDto.coverImage ?: "",
            createdAt = apiDto.createdAt ?: 0L,
            duration = apiDto.duration ?: 0,
            genre = apiDto.genre ?: "",
            id = apiDto.id ?: "",
            releaseDate = apiDto.releaseDate ?: 0L,
            title = apiDto.title ?: "",
            updateAt = apiDto.updateAt ?: 0L,
        )
    }
}