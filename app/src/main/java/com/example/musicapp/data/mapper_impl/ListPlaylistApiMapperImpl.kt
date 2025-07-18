package com.example.musicapp.data.mapper_impl

import com.example.musicapp.common.ApiMapper
import com.example.musicapp.data.model.PlaylistDto
import com.example.musicapp.data.model.SongDto
import com.example.musicapp.domain.model.Playlist
import com.example.musicapp.domain.model.Song

class ListPlaylistApiMapperImpl(
    private val apiSongMapper: ApiMapper<SongDto, Song>
) : ApiMapper<List<PlaylistDto>, List<Playlist>> {

    override fun mapToDomain(apiDto: List<PlaylistDto>): List<Playlist> {
        return apiDto.map { playlist ->
            Playlist(
                id = playlist.id ?: "",
                name = playlist.name ?: "",
                description = playlist.description ?: "",
                coverImage = playlist.coverImage ?: "",
                userId = playlist.userId ?: "",
                songs = playlist.songs?.map { song ->
                    apiSongMapper.mapToDomain(apiDto = song)
                } ?: emptyList(),
                createdAt = playlist.createdAt ?: 0L,
                updateAt = playlist.updateAt ?: 0L
            )
        }
    }
}