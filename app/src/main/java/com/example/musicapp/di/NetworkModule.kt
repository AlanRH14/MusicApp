package com.example.musicapp.di

import com.example.musicapp.data.local.preferences.SessionManagerImpl
import com.example.musicapp.data.remote.api.AuthApiService
import com.example.musicapp.data.remote.api.HomeApiService
import com.example.musicapp.data.remote.api.PlaylistApiService
import com.example.musicapp.data.remote.api.interceptors.AuthInterceptor
import com.example.musicapp.domain.repository.SessionManager
import com.example.musicapp.utils.constants.AppConstants.BASE_URL
import com.example.musicapp.utils.constants.QualifiersConstants
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

private val json = Json {
    coerceInputValues = true
    ignoreUnknownKeys = true
}

private val contentType = "application/json".toMediaType()

val networkModule = module {
    single<CoroutineDispatcher>(QualifiersConstants.DISPATCHERS_IO) { Dispatchers.IO }

    single<SessionManager> {
        SessionManagerImpl(
            dataStore = get(),
            ioDispatcher = get(QualifiersConstants.DISPATCHERS_IO)
        )
    }

    single { AuthInterceptor(sessionManagerImpl = get()) }

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .addInterceptor(get<AuthInterceptor>())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    single {
        get<Retrofit>().create(AuthApiService::class.java)
    }

    single {
        get<Retrofit>().create(HomeApiService::class.java)
    }

    single {
        get<Retrofit>().create(PlaylistApiService::class.java)
    }
}
