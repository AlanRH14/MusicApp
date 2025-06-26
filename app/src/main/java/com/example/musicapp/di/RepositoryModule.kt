package com.example.musicapp.di

import com.example.musicapp.data.local.preferences.DataStoreHandleImpl
import com.example.musicapp.data.remote.repository.AuthenticationRepositoryImpl
import com.example.musicapp.data.remote.repository.HomeRepositoryImpl
import com.example.musicapp.data.remote.repository.MusicRepositoryImp
import com.example.musicapp.domain.repository.AuthenticationRepository
import com.example.musicapp.domain.repository.DataStoreHandle
import com.example.musicapp.domain.repository.HomeRepository
import com.example.musicapp.domain.repository.MusicRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthenticationRepository> {
        AuthenticationRepositoryImpl(
            remoteDataSource = get(),
            localDataSource = get(),
            apiLoginMapper = get(named("LoginApiMapper")),
            apiUserMapper = get(named("UserApiMapper"))
        )
    }

    single<DataStoreHandle> {
        DataStoreHandleImpl(get())
    }

    single<HomeRepository> {
        HomeRepositoryImpl(
            apiService = get(),
            apiHomeMapper = get(named("HomeApiMapper"))
        )
    }

    single<MusicRepository> {
        MusicRepositoryImp(apiService = get(), apiSongMapper = get())
    }
}