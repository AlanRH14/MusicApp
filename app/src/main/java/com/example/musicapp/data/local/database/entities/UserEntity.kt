package com.example.musicapp.data.local.database.entities

import androidx.room.Entity
import com.example.musicapp.utils.DatabaseConstants.USER_TABLE
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = USER_TABLE)
data class UserEntity(
    @serval token: String,
    val name: String,
    val profilePicture: String
)
