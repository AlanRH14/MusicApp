package com.example.musicapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.musicapp.data.local.database.entities.UserEntity

@Dao
interface UserDao: MusicAppDao<UserEntity> {
    @Query("""SELECT * FROM user_table""")
    fun getUserLocalData(): UserEntity?
}