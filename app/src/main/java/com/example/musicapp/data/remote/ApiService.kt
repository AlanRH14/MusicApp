package com.example.musicapp.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("endpoint")
    suspend fun getSomething(): Response<String>
}