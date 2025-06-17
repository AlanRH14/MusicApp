package com.example.musicapp.domain.repository

import com.example.musicapp.data.model.reponse.LoginResponse
import com.example.musicapp.data.model.request.LoginRequest
import com.example.musicapp.data.model.request.RegisterRequest
import com.example.musicapp.utils.Resource

interface AuthenticationRepository {

    suspend fun login(loginRequest: LoginRequest): Resource<LoginResponse>

    suspend fun register(registerRequest: RegisterRequest): Resource<LoginResponse>
}