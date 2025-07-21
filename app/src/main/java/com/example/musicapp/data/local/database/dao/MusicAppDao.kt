package com.example.musicapp.data.local.database.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface MusicAppDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entities: T)
}