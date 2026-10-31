package com.example.musicapp.data.remote.api

import com.example.musicapp.data.model.SongDto
import com.example.musicapp.data.model.reponse.HomeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface HomeApiService {
    @GET("/home")
    suspend fun getHome(
        @Header("Authorization") token: String
    ): Response<HomeResponse>

    @GET("/songs/{id}")
    suspend fun getSongByID(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<SongDto>
}
