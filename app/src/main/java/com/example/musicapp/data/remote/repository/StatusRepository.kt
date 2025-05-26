package com.example.musicapp.data.remote.repository

import com.example.musicapp.data.remote.api.ApiService
import org.koin.core.annotation.Single

@Single
class StatusRepository(
    private val apiService: ApiService
) {

    suspend fun getStatus(): String {
        return apiService.getSomething().body()?.get("status") ?: "Failed"
    }
}