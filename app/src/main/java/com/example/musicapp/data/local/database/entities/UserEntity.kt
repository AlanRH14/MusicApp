package com.example.musicapp.data.local.database.entities

import androidx.room.Entity
import com.example.musicapp.utils.DatabaseConstants.USER_TABLE
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = USER_TABLE)
data class UserEntity(
    @SerialName("token") val token: String,
    @SerialName("email") val email: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("profilePicture") val profilePicture: String? = null,
    @SerialName("createdAt") val createdAt: Long? = null,
    @SerialName("updateAt") val updateAt: Long? = null
)
