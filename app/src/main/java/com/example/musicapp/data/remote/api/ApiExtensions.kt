package com.example.musicapp.data.remote.api

import retrofit2.Response

fun <T> Response<T>.extractErrorMessage(): String {
    val errorBody = errorBody()?.string()
    return errorBody.takeIf { !it.isNullOrEmpty() } ?: message()
}