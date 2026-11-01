package com.example.musicapp.data.mappers.playlist

import com.example.musicapp.common.Mapper
import com.example.musicapp.data.model.PlaylistDto
import com.example.musicapp.data.model.SongDto
import com.example.musicapp.domain.model.Playlist
import com.example.musicapp.domain.model.Song

class PlaylistMapperImpl(
    private val apiSongMapper: Mapper<SongDto, Song>
): Mapper<PlaylistDto, Playlist> {

    override fun mapTo(input: PlaylistDto): Playlist {
        return Playlist(
            id = input.id ?: "",
            name = input.name ?: "",
            description = input.description ?: "",
            coverImage = input.coverImage ?: "",
            userId = input.userId ?: "",
            songs = input.songs?.map { song ->
                apiSongMapper.mapTo(input = song)
            } ?: emptyList(),
            songCount = input.songCount ?: 0,
            createdAt = input.createdAt ?: 0L,
            updateAt = input.updateAt ?: 0L
        )
    }
}
