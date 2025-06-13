package com.example.musicapp.data.model.reponse

import com.example.musicapp.data.model.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("token") val token: String? = null,
    @SerialName("user") val user: User? = User()
)
