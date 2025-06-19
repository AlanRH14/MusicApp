package com.example.musicapp.data.remote.api

import com.example.musicapp.common.Resource
import retrofit2.Response

suspend fun <T> safeApiCall(
    apiCall: suspend () -> Response<T>
): Resource<T> {
    return try {
        val response = apiCall()

        if (response.isSuccessful) {
            val body = response.body()

            if (body != null) {
                Resource.Success(data = body)
            } else {
                Resource.Error(message = "Response body is null")
            }
        } else {
            Resource.Error(message = "Error: ${response.code()} - ${response.message()}")
        }
    } catch (e: Exception) {
        Resource.Error(message = "${e.cause}: ${e.message}", throwable = e)
    }
}