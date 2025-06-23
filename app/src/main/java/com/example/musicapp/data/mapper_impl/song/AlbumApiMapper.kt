package com.example.musicapp.data.mapper_impl.song

import com.example.musicapp.common.ApiMapper
import com.example.musicapp.data.model.AlbumDto
import com.example.musicapp.data.model.ArtistDto
import com.example.musicapp.data.model.SongDto
import com.example.musicapp.domain.model.Album
import com.example.musicapp.domain.model.Artist
import com.example.musicapp.domain.model.Song

class AlbumApiMapperImpl(
    private val apiArtistMapper: ApiMapper<ArtistDto, Artist>,
    private val apiSongMapper: ApiMapper<SongDto, Song>
) : ApiMapper<AlbumDto, Album> {

    override fun mapToDomain(apiDto: AlbumDto): Album {
        return Album(
            artist = apiArtistMapper.mapToDomain(apiDto = apiDto.artist ?: ArtistDto()),
            coverImage = apiDto.coverImage ?: "",
            genre = apiDto.genre ?: "",
            id = apiDto.id ?: "",
            releaseDate = apiDto.releaseDate ?: 0L,
            songs = apiDto.song?.map { song ->
                apiSongMapper.mapToDomain(apiDto = song)
            } ?: emptyList(),
            title = apiDto.title ?: ""
        )
    }
}