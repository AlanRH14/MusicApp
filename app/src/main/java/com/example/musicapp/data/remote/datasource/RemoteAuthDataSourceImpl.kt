package com.example.musicapp.data.remote.datasource

import com.example.musicapp.data.model.reponse.LoginResponse
import com.example.musicapp.data.model.request.LoginRequest
import com.example.musicapp.data.model.request.RegisterRequest
import com.example.musicapp.data.remote.api.ApiService

class RemoteAuthDataSourceImpl(
    private val apiService: ApiService
) : RemoteAuthDataSource {

    override suspend fun login(loginRequest: LoginRequest): LoginResponse {
        TODO("Not yet implemented")
    }

    override suspend fun register(registerRequest: RegisterRequest): LoginResponse {
        TODO("Not yet implemented")
    }
}