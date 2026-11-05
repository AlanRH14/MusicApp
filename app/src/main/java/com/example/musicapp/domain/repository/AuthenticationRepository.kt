package com.example.musicapp.domain.repository

import com.example.musicapp.common.Resource
import com.example.musicapp.domain.model.AuthenticationData
import com.example.musicapp.domain.model.User

interface AuthenticationRepository {
    suspend fun login(authData: AuthenticationData): Resource<User>

    suspend fun register(authData: AuthenticationData): Resource<User>
}
