package com.example.musicapp.data.remote.datasource

import com.example.musicapp.data.model.reponse.LoginResponse
import com.example.musicapp.data.model.request.LoginRequest
import com.example.musicapp.data.model.request.RegisterRequest
import com.example.musicapp.data.remote.api.ApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteAuthDataSourceImpl(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RemoteAuthDataSource {

    override suspend fun login(loginRequest: LoginRequest): LoginResponse = withContext(ioDispatcher) {
        val response = apiService.login(loginRequest = loginRequest)
        if (response.isSuccessful) {
            response.body() ?: LoginResponse()
        } else {
            throw Exception(response.message())
        }
    }

    override suspend fun register(registerRequest: RegisterRequest): LoginResponse {
        val response = apiService.register(registerRequest = registerRequest)
        return if (response.isSuccessful) {
            response.body() ?: LoginResponse()
        }else {
            throw Exception(response.message())
        }
    }
}