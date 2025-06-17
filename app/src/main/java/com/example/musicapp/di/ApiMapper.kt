package com.example.musicapp.di

import com.example.musicapp.common.music.ApiMapper
import com.example.musicapp.data.mapper_impl.artist.ArtistApiMapperImpl
import com.example.musicapp.data.mapper_impl.home.HomeApiMapperImpl
import com.example.musicapp.data.mapper_impl.login.LoginApiMapperImpl
import com.example.musicapp.data.mapper_impl.song.SongApiMapperImpl
import com.example.musicapp.data.mapper_impl.user.UserApiMapperImpl
import com.example.musicapp.data.model.ArtistDto
import com.example.musicapp.data.model.SongDto
import com.example.musicapp.data.model.UserDto
import com.example.musicapp.data.model.reponse.HomeResponse
import com.example.musicapp.data.model.reponse.LoginResponse
import com.example.musicapp.domain.model.Artist
import com.example.musicapp.domain.model.Home
import com.example.musicapp.domain.model.Login
import com.example.musicapp.domain.model.Song
import com.example.musicapp.domain.model.User
import org.koin.dsl.module

val apiMapperModule = module {
    single<ApiMapper<UserDto, User>> { UserApiMapperImpl() }
    single<ApiMapper<ArtistDto, Artist>> { ArtistApiMapperImpl() }

    single<ApiMapper<LoginResponse, Login>> {
        LoginApiMapperImpl(get<ApiMapper<UserDto, User>>())
    }
    single<ApiMapper<SongDto, Song>> {
        SongApiMapperImpl(apiArtistMapper = get<ApiMapper<ArtistDto, Artist>>())
    }
    single<ApiMapper<HomeResponse, Home>> {
        HomeApiMapperImpl(apiSongMapper = get<ApiMapper<SongDto, Song>>())
    }
}