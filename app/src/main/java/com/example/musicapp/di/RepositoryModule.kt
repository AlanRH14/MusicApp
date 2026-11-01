package com.example.musicapp.di

import com.example.musicapp.data.remote.repository.AuthenticationRepositoryImpl
import com.example.musicapp.data.remote.repository.HomeRepositoryImpl
import com.example.musicapp.data.remote.repository.MusicRepositoryImpl
import com.example.musicapp.data.remote.repository.PlaylistRepositoryImpl
import com.example.musicapp.domain.repository.AuthenticationRepository
import com.example.musicapp.domain.repository.HomeRepository
import com.example.musicapp.domain.repository.MusicRepository
import com.example.musicapp.domain.repository.PlaylistRepository
import com.example.musicapp.utils.QualifiersConstants
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthenticationRepository> {
        AuthenticationRepositoryImpl(
            remoteDataSource = get(),
            localDataSource = get(),
            apiLoginMapper = get(QualifiersConstants.LOGIN_MAPPER),
            apiUserMapper = get(QualifiersConstants.USER_MAPPER),
            ioDispatcher = get(QualifiersConstants.DISPATCHERS_IO),
        )
    }

    single<HomeRepository> {
        HomeRepositoryImpl(
            homeApiService = get(),
            apiHomeMapper = get(QualifiersConstants.HOME_MAPPER),
            userLocalDataSource = get(),
            ioDispatcher = get(QualifiersConstants.DISPATCHERS_IO)
        )
    }

    single<MusicRepository> {
        MusicRepositoryImpl(
            homeApiService = get(),
            apiSongMapper = get(QualifiersConstants.SONG_MAPPER),
            userLocalDataSource = get()
        )
    }

    single<PlaylistRepository>{
        PlaylistRepositoryImpl(
            apiService = get(),
            apiPlaylistMapper = get(QualifiersConstants.PLAYLIST_MAPPER),
            userLocalDataSource = get(),
            ioDispatcher = get(QualifiersConstants.DISPATCHERS_IO)
        )
    }
}