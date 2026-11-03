package com.example.musicapp.domain.model

data class User(
    val token: String = "",
    val id: String = "",
    val email: String = "",
    val name: String = "LordMiau",
    val profilePicture: String = "",
    val createdAt: Long = 0L,
    val updateAt: Long = 0L,
)
