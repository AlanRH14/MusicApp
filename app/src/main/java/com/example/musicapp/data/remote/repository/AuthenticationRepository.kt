package com.example.musicapp.data.remote.repository

import com.example.musicapp.data.model.LoginRequest
import com.example.musicapp.data.model.LoginResponse
import com.example.musicapp.data.remote.api.ApiService
import com.example.musicapp.utils.Resource
import org.koin.core.annotation.Single

@Single
class AuthenticationRepository(
    private val apiService: ApiService
) {

    suspend fun login(loginRequest: LoginRequest): Resource<LoginResponse> {
        return try {
            val response = apiService.login(loginRequest)
            if (response.isSuccessful) {
                response.body()?.let { res ->
                    Resource.Success(res)
                } ?: Resource.Success(LoginResponse())
            } else {
                Resource.Error(message = "Login failed")
            }
        } catch (e: Exception) {
            Resource.Error(message = "Network error: ${e.message}")
        }
    }
}