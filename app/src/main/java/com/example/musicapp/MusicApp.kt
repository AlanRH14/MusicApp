package com.example.musicapp

import android.app.Application
import com.example.musicapp.di.apiMapperModule
import com.example.musicapp.di.appModule
import com.example.musicapp.di.dataStoreModule
import com.example.musicapp.di.datasourceModule
import com.example.musicapp.di.networkModule
import com.example.musicapp.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class MusicApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MusicApp)
            androidLogger(Level.DEBUG)
            modules(
                networkModule,
                apiMapperModule,
                datasourceModule,
                repositoryModule,
                dataStoreModule,
                appModule
            )
        }
    }
}