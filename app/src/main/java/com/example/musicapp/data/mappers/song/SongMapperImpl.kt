package com.example.musicapp.data.mappers.song

import com.example.musicapp.common.Mapper
import com.example.musicapp.data.model.ArtistDto
import com.example.musicapp.data.model.SongDto
import com.example.musicapp.domain.model.Artist
import com.example.musicapp.domain.model.Song

class SongMapperImpl(
    private val apiArtistMapper: Mapper<ArtistDto, Artist>
) : Mapper<SongDto, Song> {

    override fun mapTo(input: SongDto): Song {
        return Song(
            artist = apiArtistMapper.mapTo(input = input.artist ?: ArtistDto()),
            audioUrl = input.audioUrl ?: "",
            coverImage = input.coverImage ?: "",
            createdAt = input.createdAt ?: 0L,
            duration = input.duration ?: 0L,
            genre = input.genre ?: "",
            id = input.id ?: "",
            releaseDate = input.releaseDate ?: 0L,
            title = input.title ?: "",
            updateAt = input.updateAt ?: 0L,
        )
    }
}
