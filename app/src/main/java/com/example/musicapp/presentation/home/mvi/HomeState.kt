package com.example.musicapp.presentation.home.mvi

import com.example.musicapp.domain.model.Home
import com.example.musicapp.domain.model.User

data class HomeState(
    val isLoading: Boolean = false,
    val user: User = User(),
    val homData: Home = Home(),
    val error: String? = null
)
