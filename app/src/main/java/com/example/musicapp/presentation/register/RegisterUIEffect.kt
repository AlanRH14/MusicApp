package com.example.musicapp.presentation.register

sealed interface RegisterUIEffect {
    data object OnChangeName: RegisterUIEffect
    data object OnChangeEmail: RegisterUIEffect
    data object OnChangePassword: RegisterUIEffect
    data object OnChangeConfirmPassword: RegisterUIEffect
    data object OnRegisterClicked: RegisterUIEffect
    data object OnLoginClicked: RegisterUIEffect
}