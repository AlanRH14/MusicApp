package com.example.musicapp.data.mappers.home

import com.example.musicapp.common.Mapper
import com.example.musicapp.data.model.AlbumDto
import com.example.musicapp.data.model.SongDto
import com.example.musicapp.data.model.reponse.HomeResponse
import com.example.musicapp.domain.model.Album
import com.example.musicapp.domain.model.Home
import com.example.musicapp.domain.model.Song

class HomeMapperImpl(
    private val apiSongMapper: Mapper<SongDto, Song>,
    private val apiAlbumMapper: Mapper<AlbumDto, Album>
) : Mapper<HomeResponse, Home> {

    override fun mapTo(apiDto: HomeResponse): Home {
        return Home(
            continueListening = apiDto.continueListening?.map { song ->
                apiSongMapper.mapTo(song)
            } ?: emptyList(),
            topMixes = apiDto.topMixes?.map { album ->
                apiAlbumMapper.mapTo(album)
            } ?: emptyList(),
            recommendedSong = apiDto.recommendedSong?.map { song ->
                apiSongMapper.mapTo(song)
            } ?: emptyList(),
        )
    }
}
