package com.example.musicapp.data.local.datasource

import com.example.musicapp.data.local.database.entities.UserEntity

interface UserLocalDataSource {
    suspend fun saveUser(user: UserEntity)
    suspend fun getUser(): UserEntity?
}
