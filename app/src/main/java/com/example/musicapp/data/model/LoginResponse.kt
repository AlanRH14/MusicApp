package com.example.musicapp.data.model

import kotlinx.serialization.SerialName

data class LoginResponse(
    @SerialName("token") val token: String? = null,
    @SerialName("user") val user: User? = User()
)
