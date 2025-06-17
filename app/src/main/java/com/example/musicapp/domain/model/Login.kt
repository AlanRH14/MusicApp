package com.example.musicapp.domain.model

data class Login(
    val token: String = "",
    val user: User = User()
)
