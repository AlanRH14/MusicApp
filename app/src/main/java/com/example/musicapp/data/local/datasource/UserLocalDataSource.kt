package com.example.musicapp.data.local.datasource

import com.example.musicapp.data.local.database.entities.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {
    suspend fun saveUser(user: UserEntity)
    fun getUser(): Flow<UserEntity?>
}
