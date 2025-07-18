package com.example.musicapp.data.mapper_impl.playlist

import com.example.musicapp.common.ApiMapper
import com.example.musicapp.data.model.PlaylistDto
import com.example.musicapp.data.model.SongDto
import com.example.musicapp.domain.model.Playlist
import com.example.musicapp.domain.model.Song

class PlaylistApiMapperImpl(
    private val apiSongMapper: ApiMapper<SongDto, Song>
): ApiMapper<PlaylistDto, Playlist> {

    override fun mapToDomain(apiDto: PlaylistDto): Playlist {
        return Playlist(
            id = apiDto.id ?: "",
            name = apiDto.name ?: "",
            description = apiDto.description ?: "",
            coverImage = apiDto.coverImage ?: "",
            userId = apiDto.userId ?: "",
            songs = apiDto.songs?.map { song ->
                apiSongMapper.mapToDomain(apiDto = song)
            } ?: emptyList(),
            createdAt = apiDto.createdAt ?: 0L,
            updateAt = apiDto.updateAt ?: 0L
        )
    }
}