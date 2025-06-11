package com.example.musicapp.presentation.login

data class LoginState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val email: String? = null,
    val password: String? = null,
    val isPasswordVisibility: Boolean = false,
    val isEmailError: Boolean = false,
    val isPasswordError: Boolean = false,
    val rememberMeActive: Boolean = false
)
