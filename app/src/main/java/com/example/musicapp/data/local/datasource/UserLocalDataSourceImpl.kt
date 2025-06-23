package com.example.musicapp.data.local.datasource

import com.example.musicapp.data.local.database.UserDao
import com.example.musicapp.data.local.database.entities.UserEntity

class UserLocalDataSourceImpl(
    private val userDao: UserDao
): UserLocalDataSource {

    override suspend fun savaUser(user: UserEntity) {
        userDao.insert(entities = user)
    }

    override suspend fun getUser(): UserEntity? {
        userDao.getUserLocalData()
    }
}