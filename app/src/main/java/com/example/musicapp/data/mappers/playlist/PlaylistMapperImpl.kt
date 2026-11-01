package com.example.musicapp.data.mappers.playlist

import com.example.musicapp.common.Mapper
import com.example.musicapp.data.model.PlaylistDto
import com.example.musicapp.data.model.SongDto
import com.example.musicapp.domain.model.Playlist
import com.example.musicapp.domain.model.Song

class PlaylistMapperImpl(
    private val apiSongMapper: Mapper<SongDto, Song>
): Mapper<PlaylistDto, Playlist> {

    override fun mapTo(apiDto: PlaylistDto): Playlist {
        return Playlist(
            id = apiDto.id ?: "",
            name = apiDto.name ?: "",
            description = apiDto.description ?: "",
            coverImage = apiDto.coverImage ?: "",
            userId = apiDto.userId ?: "",
            songs = apiDto.songs?.map { song ->
                apiSongMapper.mapTo(apiDto = song)
            } ?: emptyList(),
            songCount = apiDto.songCount ?: 0,
            createdAt = apiDto.createdAt ?: 0L,
            updateAt = apiDto.updateAt ?: 0L
        )
    }
}
