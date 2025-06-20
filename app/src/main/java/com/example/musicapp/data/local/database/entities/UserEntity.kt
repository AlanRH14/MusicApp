package com.example.musicapp.data.local.database.entities

import androidx.room.Entity
import com.example.musicapp.utils.DatabaseConstants.USER_TABLE
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = USER_TABLE)
data class UserEntity(
    @SerialName("token") val token: String,
    @SerialName("name") val name: String,
    @SerialName("profilePicture") val profilePicture: String
)
