package com.example.musicapp.data.remote.api

import com.example.musicapp.common.Resource
import retrofit2.Response

suspend fun <T> safeApiCall(
    apiCall: suspend () -> Response<T>,
    throwable: Throwable
): Resource<T> {
    return try {
        val response = apiCall()

        if (response.isSuccessful) {
            val body = response.body()

            if (body != null) {
                Resource.Success(data = body)
            } else {
                Resource.Error(throwable = throwable)
            }
        } else {
            Resource.Error(throwable = Exception("Error: ${response.code()} - ${response.message()}"))
        }
    } catch (error: Exception) {
        Resource.Error(throwable = Exception(error))
    }
}