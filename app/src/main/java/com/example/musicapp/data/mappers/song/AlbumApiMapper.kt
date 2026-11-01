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

    override fun mapTo(input: AlbumDto): Album {
        return Album(
            artist = apiArtistMapper.mapTo(input = input.artist ?: ArtistDto()),
            coverImage = input.coverImage ?: "",
            genre = input.genre ?: "",
            id = input.id ?: "",
            releaseDate = input.releaseDate ?: 0L,
            songs = input.song?.map { song ->
                apiSongMapper.mapTo(input = song)
            } ?: emptyList(),
            title = input.title ?: ""
        )
    }
}
