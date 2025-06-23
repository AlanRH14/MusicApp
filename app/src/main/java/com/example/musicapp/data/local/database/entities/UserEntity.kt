package com.example.musicapp.data.local.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.musicapp.utils.DatabaseConstants.USER_TABLE
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = USER_TABLE)
data class UserEntity(
    @PrimaryKey
    @SerialName("token") val token: String,
    @SerialName("id") val id: String? = null,
    @SerialName("email") val email: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("profilePicture") val profilePicture: String? = null,
    @SerialName("createdAt") val createdAt: Long? = null,
    @SerialName("updateAt") val updateAt: Long? = null
)
