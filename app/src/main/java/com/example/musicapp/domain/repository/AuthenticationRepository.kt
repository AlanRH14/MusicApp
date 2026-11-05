package com.example.musicapp.domain.repository

import com.example.musicapp.common.Resource
import com.example.musicapp.domain.model.User

interface AuthenticationRepository {
    suspend fun login(
        email: String? = null,
        password: String? = null
    ): Resource<User>

    suspend fun register(
        email: String? = null,
        password: String? = null,
        name: String? = null
    ): Resource<User>
}
