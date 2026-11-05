package com.example.musicapp.di

import com.example.musicapp.data.service.helper.MusicAppNotificationHelper
import com.example.musicapp.utils.constants.QualifiersConstants
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val musicModule = module {
    single {
        MusicAppNotificationHelper(
            mContext = androidContext(),
            ioDispatcher = get(QualifiersConstants.DISPATCHERS_IO)
        )
    }
}