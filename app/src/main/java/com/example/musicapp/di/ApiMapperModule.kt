package com.example.musicapp.di

import com.example.musicapp.common.ApiMapper
import com.example.musicapp.data.local.database.entities.UserEntity
import com.example.musicapp.data.mappers.playlist.PlaylistApiMapperImpl
import com.example.musicapp.data.mappers.artist.ArtistApiMapperImpl
import com.example.musicapp.data.mappers.home.HomeApiMapperImpl
import com.example.musicapp.data.mappers.song.AlbumApiMapperImpl
import com.example.musicapp.data.mappers.song.SongApiMapperImpl
import com.example.musicapp.data.mappers.user.UserApiMapperImpl
import com.example.musicapp.data.mappers.user.UserEntityMapperImpl
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

val apiMapperModule = module {
    single<ApiMapper<UserEntity, User>>(QualifiersConstants.USER_MAPPER) {
        UserApiMapperImpl()
    }

    single<ApiMapper<LoginResponse, UserEntity>>(QualifiersConstants.LOGIN_MAPPER) {
        UserEntityMapperImpl()
    }

    single<ApiMapper<ArtistDto, Artist>>(QualifiersConstants.ARTIST_MAPPER) {
        ArtistApiMapperImpl()
    }

    single<ApiMapper<SongDto, Song>>(QualifiersConstants.SONG_MAPPER) {
        SongApiMapperImpl(apiArtistMapper = get(QualifiersConstants.ARTIST_MAPPER))
    }

    single<ApiMapper<AlbumDto, Album>>(QualifiersConstants.ALBUM_MAPPER) {
        AlbumApiMapperImpl(
            apiArtistMapper = get(QualifiersConstants.ARTIST_MAPPER),
            apiSongMapper = get(QualifiersConstants.SONG_MAPPER)
        )
    }

    single<ApiMapper<HomeResponse, Home>>(QualifiersConstants.HOME_MAPPER) {
        HomeApiMapperImpl(
            apiSongMapper = get(QualifiersConstants.SONG_MAPPER),
            apiAlbumMapper = get(QualifiersConstants.ALBUM_MAPPER)
        )
    }

    single<ApiMapper<PlaylistDto, Playlist>>(QualifiersConstants.PLAYLIST_MAPPER) {
        PlaylistApiMapperImpl(
            get(QualifiersConstants.SONG_MAPPER)
        )
    }
}
