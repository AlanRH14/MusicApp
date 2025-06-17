package com.example.musicapp.di

import com.example.musicapp.data.mapper_impl.artist.ArtistApiMapperImpl
import com.example.musicapp.data.mapper_impl.home.HomeApiMapperImpl
import com.example.musicapp.data.mapper_impl.login.LoginApiMapperImpl
import com.example.musicapp.data.mapper_impl.song.SongApiMapperImpl
import com.example.musicapp.data.mapper_impl.user.UserApiMapperImpl
import org.koin.dsl.module

val apiMapperModule = module {
    single { UserApiMapperImpl() }
    single { LoginApiMapperImpl(apiUserMapper = get()) }
    single { ArtistApiMapperImpl() }
    single { SongApiMapperImpl(apiArtistMapper = get()) }
    single { HomeApiMapperImpl(apiSongMapper = get()) }
}