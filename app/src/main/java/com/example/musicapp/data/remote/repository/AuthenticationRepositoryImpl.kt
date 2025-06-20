package com.example.musicapp.data.remote.repository

import com.example.musicapp.common.ApiMapper
import com.example.musicapp.data.model.request.LoginRequest
import com.example.musicapp.data.model.reponse.LoginResponse
import com.example.musicapp.data.model.request.RegisterRequest
import com.example.musicapp.data.remote.api.ApiService
import com.example.musicapp.domain.model.Login
import com.example.musicapp.domain.repository.AuthenticationRepository
import com.example.musicapp.common.Resource
import com.example.musicapp.data.local.database.entities.UserEntity
import com.example.musicapp.domain.model.User

class AuthenticationRepositoryImpl(
    private val apiService: ApiService,
    private val apiLoginMapper: ApiMapper<LoginResponse, UserEntity>
) : AuthenticationRepository {

    override suspend fun login(loginRequest: LoginRequest): Resource<User> {
        Resource.Loading

        return try {
            val response = apiService.login(loginRequest)
            if (response.isSuccessful) {
                response.body()?.let { res ->
                    Resource.Success(data = apiLoginMapper.mapToDomain(apiDto = res))
                } ?: Resource.Success(data = Login())
            } else {
                Resource.Error(message = "Login failed")
            }
        } catch (e: Exception) {
            Resource.Error(message = "Error: ${e.message}")
        }
    }

    override suspend fun register(registerRequest: RegisterRequest): Resource<User> {
        Resource.Loading

        return try {
            val response = apiService.register(registerRequest)
            if (response.isSuccessful) {
                response.body()?.let { res ->
                    Resource.Success(data = apiLoginMapper.mapToDomain(apiDto = res))
                } ?: Resource.Success(data = Login())
            } else {
                Resource.Error(message = "Registration failed")
            }
        } catch (e: Exception) {
            Resource.Error(message = "Error: ${e.message}")
        }
    }
}