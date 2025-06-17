package com.example.musicapp.di

import com.example.musicapp.common.music.ApiMapper
import com.example.musicapp.data.model.reponse.LoginResponse
import com.example.musicapp.data.remote.repository.AuthenticationRepositoryImpl
import com.example.musicapp.data.remote.repository.HomeRepositoryImpl
import com.example.musicapp.domain.model.Login
import com.example.musicapp.domain.repository.AuthenticationRepository
import com.example.musicapp.domain.repository.HomeRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthenticationRepository> {
        AuthenticationRepositoryImpl(
            apiService = get(),
            apiLoginMapper = get<ApiMapper<LoginResponse, Login>>()
        )
    }

    single<HomeRepository> {
        HomeRepositoryImpl(apiService = get(), apiHomeMapper = get())
    }
}