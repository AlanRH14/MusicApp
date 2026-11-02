package com.example.musicapp.di

import com.example.musicapp.common.Mapper
import com.example.musicapp.data.local.database.entities.UserEntity
import com.example.musicapp.data.mappers.playlist.PlaylistMapperImpl
import com.example.musicapp.data.mappers.artist.ArtistMapperImpl
import com.example.musicapp.data.mappers.home.HomeMapperImpl
import com.example.musicapp.data.mappers.song.AlbumMapperImpl
import com.example.musicapp.data.mappers.song.SongMapperImpl
import com.example.musicapp.data.mappers.user.UserEntityMapperImpl
import com.example.musicapp.data.mappers.user.UserMapperImpl
import com.example.musicapp.data.model.AlbumDto
import com.example.musicapp.data.model.ArtistDto
import com.example.musicapp.data.model.PlaylistDto
import com.example.musicapp.data.model.SongDto
import com.example.musicapp.data.model.reponse.HomeResponse
import com.example.musicapp.data.model.reponse.LoginResponse
import com.example.musicapp.domain.model.Album
import com.example.musicapp.domain.model.Artist
import com.example.musicapp.domain.model.Home
import com.example.musicapp.domain.model.Playlist
import com.example.musicapp.domain.model.Song
import com.example.musicapp.domain.model.User
import com.example.musicapp.utils.QualifiersConstants
import org.koin.dsl.module

val mapperModule = module {
    single<Mapper<LoginResponse, User>>(QualifiersConstants.USER_MAPPER) {
        UserMapperImpl()
    }
    single<Mapper<User, UserEntity>>(QualifiersConstants.USER_ENTITY_MAPPER) {
        UserEntityMapperImpl()
    }

    single<Mapper<ArtistDto, Artist>>(QualifiersConstants.ARTIST_MAPPER) {
        ArtistMapperImpl()
    }

    single<Mapper<SongDto, Song>>(QualifiersConstants.SONG_MAPPER) {
        SongMapperImpl(apiArtistMapper = get(QualifiersConstants.ARTIST_MAPPER))
    }

    single<Mapper<AlbumDto, Album>>(QualifiersConstants.ALBUM_MAPPER) {
        AlbumMapperImpl(
            apiArtistMapper = get(QualifiersConstants.ARTIST_MAPPER),
            apiSongMapper = get(QualifiersConstants.SONG_MAPPER)
        )
    }

    single<Mapper<HomeResponse, Home>>(QualifiersConstants.HOME_MAPPER) {
        HomeMapperImpl(
            apiSongMapper = get(QualifiersConstants.SONG_MAPPER),
            apiAlbumMapper = get(QualifiersConstants.ALBUM_MAPPER)
        )
    }

    single<Mapper<PlaylistDto, Playlist>>(QualifiersConstants.PLAYLIST_MAPPER) {
        PlaylistMapperImpl(
            get(QualifiersConstants.SONG_MAPPER)
        )
    }
}
