package com.example.musicapp.presentation.home

import com.example.musicapp.data.model.HomeResponse
import com.example.musicapp.data.model.User

data class HomeState(
    val isLoading: Boolean = false,
    val user: User = User(),
    val data: HomeResponse = HomeResponse(),
    val error: String? = null
)
