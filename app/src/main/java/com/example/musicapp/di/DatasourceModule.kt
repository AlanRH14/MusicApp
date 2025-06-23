package com.example.musicapp.di

import com.example.musicapp.data.local.datasource.UserLocalDataSource
import com.example.musicapp.data.local.datasource.UserLocalDataSourceImpl
import com.example.musicapp.data.remote.datasource.RemoteAuthDataSource
import com.example.musicapp.data.remote.datasource.RemoteAuthDataSourceImpl
import org.koin.dsl.module

val DatasourceModule = module {
    single<RemoteAuthDataSource> {
        RemoteAuthDataSourceImpl(get())
    }

    single<UserLocalDataSource> {
        UserLocalDataSourceImpl(get())
    }
}