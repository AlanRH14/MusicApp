package com.example.musicapp.domain.repository

import com.example.musicapp.common.Resource
import com.example.musicapp.domain.model.AuthData
import com.example.musicapp.domain.model.User

interface AuthenticationRepository {
    suspend fun login(authData: AuthData): Resource<User>

    suspend fun register(authData: AuthData): Resource<User>
}
