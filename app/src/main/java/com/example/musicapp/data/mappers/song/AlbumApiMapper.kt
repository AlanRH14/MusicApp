package com.example.musicapp.data.mappers.song

import com.example.musicapp.common.Mapper
import com.example.musicapp.data.model.AlbumDto
import com.example.musicapp.data.model.ArtistDto
import com.example.musicapp.data.model.SongDto
import com.example.musicapp.domain.model.Album
import com.example.musicapp.domain.model.Artist
import com.example.musicapp.domain.model.Song

class AlbumMapperImpl(
    private val apiArtistMapper: Mapper<ArtistDto, Artist>,
    private val apiSongMapper: Mapper<SongDto, Song>
) : Mapper<AlbumDto, Album> {

    override fun mapTo(apiDto: AlbumDto): Album {
        return Album(
            artist = apiArtistMapper.mapTo(apiDto = apiDto.artist ?: ArtistDto()),
            coverImage = apiDto.coverImage ?: "",
            genre = apiDto.genre ?: "",
            id = apiDto.id ?: "",
            releaseDate = apiDto.releaseDate ?: 0L,
            songs = apiDto.song?.map { song ->
                apiSongMapper.mapTo(apiDto = song)
            } ?: emptyList(),
            title = apiDto.title ?: ""
        )
    }
}
