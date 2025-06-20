package com.example.musicapp.data.local.database

import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface MusicAppDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entities: T)

    suspend fun update(entities: T)
}