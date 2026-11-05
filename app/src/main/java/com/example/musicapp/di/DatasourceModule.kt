package com.example.musicapp.di

import com.example.musicapp.data.local.datasource.UserLocalDataSource
import com.example.musicapp.data.local.datasource.UserLocalDataSourceImpl
import com.example.musicapp.data.remote.datasource.RemoteAuthDataSource
import org.koin.dsl.module

val datasourceModule = module {
    single<RemoteAuthDataSource> {
        RemoteAuthDataSourceImpl(apiService = get())
    }

    single<UserLocalDataSource> {
        UserLocalDataSourceImpl(get())
    }
}