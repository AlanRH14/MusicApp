package com.example.musicapp.data.remote.datasource

import com.example.musicapp.data.model.reponse.LoginResponse
import com.example.musicapp.data.model.request.LoginRequest
import com.example.musicapp.data.model.request.RegisterRequest
import com.example.musicapp.data.remote.api.AuthApiService
import com.example.musicapp.domain.exception.NetworkException
import retrofit2.Response

class RemoteAuthDataSourceImpl(
    private val apiService: AuthApiService,
) : RemoteAuthDataSource {

    override suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return handleApiResponse { apiService.login(loginRequest) }
    }

    override suspend fun register(registerRequest: RegisterRequest): LoginResponse {
        return handleApiResponse { apiService.register(registerRequest) }
    }

    private inline fun <T> handleApiResponse(request: () -> Response<T>): T {
        val response = request()
        if (response.isSuccessful) {
            return response.body() ?: throw NetworkException.EmptyBodyException()
        } else {
            val errorBody = response.errorBody()?.string()
            throw NetworkException.HttpException(code = response.code(), message = errorBody ?: response.message())
        }
    }
}
