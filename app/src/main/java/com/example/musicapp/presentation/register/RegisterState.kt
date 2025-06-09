package com.example.musicapp.presentation.register

data class RegisterState(
    val loading: Boolean = false,
    val errorMessage: String? = null,
    val name: String? = null,
    val email: String? = null,
    val password: String? = null,
    val confirmPassword: String? = null,
    val isPasswordVisible: Boolean = false
)