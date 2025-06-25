package com.example.musicapp.data.remote.api

import com.example.musicapp.data.model.reponse.HomeResponse
import com.example.musicapp.data.model.request.LoginRequest
import com.example.musicapp.data.model.reponse.LoginResponse
import com.example.musicapp.data.model.request.RegisterRequest
import com.example.musicapp.domain.model.Song
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("/status")
    suspend fun getSomething(): Response<Map<String, String>>

    @POST("/auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

    @POST("/auth/register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): Response<LoginResponse>

    @GET("/home")
    suspend fun getHome(): Response<HomeResponse>

    @GET("/songs/{id}")
    suspend fun getSongByID(
        @Path("id") id: String
    ): Response<Song>
}