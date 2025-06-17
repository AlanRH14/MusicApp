package com.example.musicapp.data.remote.repository

import com.example.musicapp.data.mapper_impl.home.HomeApiMapperImpl
import com.example.musicapp.data.model.reponse.HomeResponse
import com.example.musicapp.data.remote.api.ApiService
import com.example.musicapp.domain.model.Home
import com.example.musicapp.domain.repository.HomeRepository
import com.example.musicapp.utils.Resource
import org.koin.core.annotation.Single

@Single
class HomeRepositoryImpl(
    private val apiService: ApiService,
    private val apiHomeMapper: HomeApiMapperImpl
) : HomeRepository {

    override suspend fun getHomeData(): Resource<Home> {
        Resource.Loading

        return try {
            val response = apiService.getHome()
            if (response.isSuccessful) {
                response.body()?.let { res ->
                    Resource.Success(data = apiHomeMapper.mapToDomain(apiDto = res))
                } ?: Resource.Success(data = Home())
            }
            Resource.Success(data = Home())
        } catch (e: Exception) {
            Resource.Error("")
        }
    }
}