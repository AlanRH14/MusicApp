package com.example.musicapp.data.local.database

import androidx.room.Query
import com.example.musicapp.data.local.database.entities.UserEntity

interface UserDao: MusicAppDao<UserEntity> {
    @Query("""SELECT * FROM user_table""")
    fun getUserLocalData(): UserEntity?
}