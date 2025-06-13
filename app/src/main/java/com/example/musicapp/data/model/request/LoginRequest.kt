package com.example.musicapp.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    @SerialName("email") val email: String? = null,
    @SerialName("password") val password: String? = null
)
