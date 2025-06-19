package com.example.musicapp.di

import com.example.musicapp.data.local.DataStoreHandleImpl
import com.example.musicapp.data.remote.repository.AuthenticationRepositoryImpl
import com.example.musicapp.data.remote.repository.HomeRepositoryImpl
import com.example.musicapp.domain.repository.AuthenticationRepository
import com.example.musicapp.domain.repository.DataStoreHandle
import com.example.musicapp.domain.repository.HomeRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthenticationRepository> {
        AuthenticationRepositoryImpl(
            apiService = get(),
            apiLoginMapper = get(named("LoginApiMapper"))
        )
    }

    single<DataStoreHandle> {
        DataStoreHandleImpl(

        )
    }

    single<HomeRepository> {
        HomeRepositoryImpl(
            apiService = get(),
            apiHomeMapper = get(named("HomeApiMapper"))
        )
    }
}