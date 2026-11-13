package com.example.musicapp.di

import com.example.musicapp.data.remote.repository.AuthenticationRepositoryImpl
import com.example.musicapp.data.remote.repository.HomeRepositoryImpl
import com.example.musicapp.data.remote.repository.MusicRepositoryImpl
import com.example.musicapp.data.remote.repository.PlaylistRepositoryImpl
import com.example.musicapp.data.service.AudioPlayerManager
import com.example.musicapp.domain.repository.AudioPlayerRepository
import com.example.musicapp.domain.repository.AuthenticationRepository
import com.example.musicapp.domain.repository.HomeRepository
import com.example.musicapp.domain.repository.MusicRepository
import com.example.musicapp.domain.repository.PlaylistRepository
import com.example.musicapp.utils.constants.QualifiersConstants
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthenticationRepository> {
        AuthenticationRepositoryImpl(
            apiService = get(),
            localDataSource = get(),
            dataStore = get(),
            userMapper = get(QualifiersConstants.USER_MAPPER),
            userEntityMapper = get(QualifiersConstants.USER_ENTITY_MAPPER),
            ioDispatcher = get(QualifiersConstants.DISPATCHERS_IO),
        )
    }

    single<HomeRepository> {
        HomeRepositoryImpl(
            homeApiService = get(),
            apiHomeMapper = get(QualifiersConstants.HOME_MAPPER),
            ioDispatcher = get(QualifiersConstants.DISPATCHERS_IO)
        )
    }

    single<MusicRepository> {
        MusicRepositoryImpl(
            homeApiService = get(),
            apiSongMapper = get(QualifiersConstants.SONG_MAPPER),
            ioDispatcher = get(QualifiersConstants.DISPATCHERS_IO)
        )
    }

    single<PlaylistRepository> {
        PlaylistRepositoryImpl(
            apiService = get(),
            apiPlaylistMapper = get(QualifiersConstants.PLAYLIST_MAPPER),
            ioDispatcher = get(QualifiersConstants.DISPATCHERS_IO)
        )
    }

    single<AudioPlayerRepository> { AudioPlayerManager(context = androidContext()) }
}
