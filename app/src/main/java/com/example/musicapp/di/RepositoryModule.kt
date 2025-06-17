package com.example.musicapp.di

import com.example.musicapp.data.remote.repository.AuthenticationRepositoryImpl
import com.example.musicapp.domain.repository.AuthenticationRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthenticationRepository> { AuthenticationRepositoryImpl(get(), get()) }
}