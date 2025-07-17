package com.example.musicapp.domain.repository

import com.example.musicapp.data.model.request.LoginRequest
import com.example.musicapp.data.model.request.RegisterRequest
import com.example.musicapp.common.Resource
import com.example.musicapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    fun login(loginRequest: LoginRequest): Flow<Resource<User>>

    fun register(registerRequest: RegisterRequest): Flow<Resource<User>>
}