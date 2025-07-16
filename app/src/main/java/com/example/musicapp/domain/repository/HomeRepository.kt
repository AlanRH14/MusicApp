package com.example.musicapp.domain.repository

import com.example.musicapp.domain.model.Home
import com.example.musicapp.common.Resource

interface HomeRepository {
    suspend fun getHomeData(): Resource<Home>
}