package com.example.musicapp.presentation.home

import com.example.musicapp.domain.model.Home
import com.example.musicapp.domain.model.User

data class HomeState(
    val isLoading: Boolean = false,
    val userDto: User = User(),
    val homData: Home = Home(),
    val error: String? = null
)
