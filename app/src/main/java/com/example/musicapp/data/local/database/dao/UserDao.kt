package com.example.musicapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.musicapp.data.local.database.entities.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao: MusicAppDao<UserEntity> {
    @Query("SELECT * FROM user_table")
    fun getUserLocalData(): Flow<UserEntity?>
}
