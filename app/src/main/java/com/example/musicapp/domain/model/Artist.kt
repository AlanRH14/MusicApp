package com.example.musicapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Artist(
    val bio: String = "",
    val createdAt: Long = 0L,
    val id: String = "",
    val name: String = "",
    val profilePicture: String = "",
    val updatedAt: Long = 0L,
)
