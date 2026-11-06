package com.example.musicapp.data.remote.api.utils

import retrofit2.Response

fun <T> Response<T>.getErrorMessage(): String {
    val errorBody = errorBody()?.string()
    return errorBody.takeIf { !it.isNullOrEmpty() } ?: message()
}