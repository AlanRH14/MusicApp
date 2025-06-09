package com.example.musicapp.data.model

import kotlinx.serialization.SerialName

data class LoginRequest(
    @SerialName("email") val email: String? = null,
    @SerialName("password") val password: String? = null
)
