package com.example.musicapp.di

import com.example.musicapp.data.local.preferences.DataStoreHandleImpl
import com.example.musicapp.data.remote.repository.AuthenticationRepositoryImpl
import com.example.musicapp.data.remote.repository.HomeRepositoryImpl
import com.example.musicapp.data.remote.repository.MusicRepositoryImpl
import com.example.musicapp.data.remote.repository.PlaylistRepositoryImpl
import com.example.musicapp.domain.repository.AuthenticationRepository
import com.example.musicapp.domain.repository.DataStoreHandle
import com.example.musicapp.domain.repository.HomeRepository
import com.example.musicapp.domain.repository.MusicRepository
import com.example.musicapp.domain.repository.PlaylistRepository
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthenticationRepository> {
        AuthenticationRepositoryImpl(
            remoteDataSource = get(),
            localDataSource = get(),
            apiLoginMapper = get(named("LoginApiMapper")),
            apiUserMapper = get(named("UserApiMapper")),
            ioDispatcher = Dispatchers.IO,
        )
    }

    single<DataStoreHandle> {
        DataStoreHandleImpl(get())
    }

    single<HomeRepository> {
        HomeRepositoryImpl(
            apiService = get(),
            apiHomeMapper = get(named("HomeApiMapper")),
            userLocalDataSource = get()
        )
    }

    single<MusicRepository> {
        MusicRepositoryImpl(
            apiService = get(),
            apiSongMapper = get(named("SongApiMapper")),
            userLocalDataSource = get()
        )
    }

    single<PlaylistRepository>{
        PlaylistRepositoryImpl(
            apiService = get(),
            apiPlaylistMapper = get(named("PlaylistApiMapper")),
            userLocalDataSource = get(),
            ioDispatcher = Dispatchers.IO
        )
    }
}