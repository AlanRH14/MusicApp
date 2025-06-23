package com.example.musicapp.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val DatabaseModule = module {

    single {

        Room.databaseBuilder(
            androidContext(),
        )
    }
}