package com.example.musicapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtistDto(
    @SerialName("bio")
    val bio: String? = null,
    @SerialName("createdAt")
    val createdAt: Long? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("profilePicture")
    val profilePicture: String? = null,
    @SerialName("updatedAt")
    val updatedAt: Long? = null
)