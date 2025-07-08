package com.example.musicapp.di

import com.example.musicapp.data.service.helper.MusicAppNotificationHelper
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val musicModule = module {
    single {
        MusicAppNotificationHelper(androidContext())
    }
}