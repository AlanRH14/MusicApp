package com.example.musicapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerialName("id") val id: String? = null,
    @SerialName("email") val email: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("profilePicture") val profilePicture: String? = null,
    @SerialName("createdAt") val createdAt: Long? = null,
    @SerialName("updateAt") val updateAt: Long? = null
)