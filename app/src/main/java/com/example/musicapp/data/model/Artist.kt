package com.example.musicapp.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Artist(
    @SerialName("bio")
    val bio: String?,
    @SerialName("createdAt")
    val createdAt: Long?,
    @SerialName("id")
    val id: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("profilePicture")
    val profilePicture: String?,
    @SerialName("updatedAt")
    val updatedAt: Long?
)