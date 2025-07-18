package com.example.musicapp.di

import com.example.musicapp.common.ApiMapper
import com.example.musicapp.data.local.database.entities.UserEntity
import com.example.musicapp.data.mapper_impl.playlist.PlaylistApiMapperImpl
import com.example.musicapp.data.mapper_impl.artist.ArtistApiMapperImpl
import com.example.musicapp.data.mapper_impl.home.HomeApiMapperImpl
import com.example.musicapp.data.mapper_impl.song.AlbumApiMapperImpl
import com.example.musicapp.data.mapper_impl.song.SongApiMapperImpl
import com.example.musicapp.data.mapper_impl.user.UserApiMapperImpl
import com.example.musicapp.data.mapper_impl.user.UserEntityMapperImpl
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
import org.koin.core.qualifier.named
import org.koin.dsl.module

val apiMapperModule = module {
    single<ApiMapper<UserEntity, User>>(named("UserApiMapper")) {
        UserApiMapperImpl()
    }

    single<ApiMapper<LoginResponse, UserEntity>>(named("LoginApiMapper")) {
        UserEntityMapperImpl()
    }


    single<ApiMapper<ArtistDto, Artist>>(named("ArtistApiMapper")) {
        ArtistApiMapperImpl()
    }

    single<ApiMapper<SongDto, Song>>(named("SongApiMapper")) {
        SongApiMapperImpl(get(named("ArtistApiMapper")))
    }

    single<ApiMapper<AlbumDto, Album>>(named("AlbumApiMapper")) {
        AlbumApiMapperImpl(
            get(named("ArtistApiMapper")),
            get(named("SongApiMapper"))
        )
    }

    single<ApiMapper<HomeResponse, Home>>(named("HomeApiMapper")) {
        HomeApiMapperImpl(
            get(named("SongApiMapper")),
            get(named("AlbumApiMapper"))
        )
    }

    single<ApiMapper<List<PlaylistDto>, List<Playlist>>>(named("ListPlaylistApiMapper")) {
        ListPlaylistApiMapperImpl(
            get(named("SongApiMapper"))
        )
    }

    single<ApiMapper<PlaylistDto, Playlist>>(named("PlaylistApiMapper")) {
        PlaylistApiMapperImpl(
            get(named("SongApiMapper"))
        )
    }
}