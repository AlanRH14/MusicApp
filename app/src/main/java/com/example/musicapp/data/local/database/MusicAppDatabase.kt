package com.example.musicapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.musicapp.data.local.database.dao.UserDao
import com.example.musicapp.data.local.database.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class MusicAppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
}