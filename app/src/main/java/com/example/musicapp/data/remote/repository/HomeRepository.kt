package com.example.musicapp.data.remote.repository

import com.example.musicapp.data.model.HomeResponseDto
import com.example.musicapp.data.remote.api.ApiService
import com.example.musicapp.utils.Resource
import org.koin.core.annotation.Single

@Single
class HomeRepository(
    private val apiService: ApiService
) {

    suspend fun getHomeData(): Resource<HomeResponseDto> {
        Resource.Loading

        return try {
            val response = apiService.getHome()
            if (response.isSuccessful) {
                response.body()?.let { res ->
                    Resource.Success(data = res)
                } ?: Resource.Success(data = response)
            }
            Resource.Success(data = HomeResponseDto())
        } catch (e: Exception) {
            Resource.Error("")
        }
    }
}