package com.example.musicapp.data.remote.api

import com.example.musicapp.data.model.response.LoginResponse
import com.example.musicapp.data.model.request.LoginRequest
import com.example.musicapp.data.model.request.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApiService {
    @Headers("No-Authorization: true")
    @POST("/auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

    @POST("/auth/register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): Response<LoginResponse>
}
