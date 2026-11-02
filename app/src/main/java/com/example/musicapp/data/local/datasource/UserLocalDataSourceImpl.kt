package com.example.musicapp.data.local.datasource

import com.example.musicapp.data.local.database.dao.UserDao
import com.example.musicapp.data.local.database.entities.UserEntity
import kotlinx.coroutines.flow.Flow

class UserLocalDataSourceImpl(
    private val userDao: UserDao
): UserLocalDataSource {

    override suspend fun saveUser(user: UserEntity) {
        userDao.insert(entities = user)
    }

    override fun getUser(): Flow<UserEntity?> {
        return userDao.getUserLocalData()
    }
}
