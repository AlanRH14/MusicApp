package com.example.musicapp.domain.repository

import com.example.musicapp.domain.model.Home
import com.example.musicapp.common.Resource
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getHomeData(): Flow<Resource<Home>>
}