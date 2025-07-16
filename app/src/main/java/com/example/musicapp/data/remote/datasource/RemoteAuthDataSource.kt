package com.example.musicapp.data.remote.datasource

import com.example.musicapp.data.model.reponse.LoginResponse
import com.example.musicapp.data.model.request.LoginRequest
import com.example.musicapp.data.model.request.RegisterRequest

interface RemoteAuthDataSource {
    suspend fun login(loginRequest: LoginRequest): LoginResponse
    suspend fun register(registerRequest: RegisterRequest): LoginResponse
}