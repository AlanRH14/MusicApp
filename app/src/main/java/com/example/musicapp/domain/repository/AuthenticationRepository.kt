package com.example.musicapp.domain.repository

import com.example.musicapp.data.model.request.LoginRequest
import com.example.musicapp.data.model.request.RegisterRequest
import com.example.musicapp.domain.model.Login
import com.example.musicapp.common.Resource
import com.example.musicapp.domain.model.User

interface AuthenticationRepository {

    suspend fun login(loginRequest: LoginRequest): Resource<User>

    suspend fun register(registerRequest: RegisterRequest): Resource<User>
}