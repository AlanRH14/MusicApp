package com.example.musicapp.di

import androidx.room.Room
import com.example.musicapp.data.local.database.MusicAppDatabase
import com.example.musicapp.utils.DatabaseConstants.DATABASE
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val DatabaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            MusicAppDatabase::class.java,
            DATABASE,
        ).build()
    }

    single { get<MusicAppDatabase>().userDao() }
}