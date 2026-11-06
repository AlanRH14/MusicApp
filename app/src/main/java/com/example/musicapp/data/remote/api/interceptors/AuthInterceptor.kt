package com.example.musicapp.data.remote.api.interceptors

import com.example.musicapp.data.local.preferences.TokenProvider
import com.example.musicapp.utils.constants.AppConstants.AUTHENTICATION_HEADER_TYPE
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val tokenProvider: TokenProvider
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val token = tokenProvider.token.value

        if (token.isEmpty()) {
            return chain.proceed(originalRequest)
        }

        val authenticatedRequest = originalRequest.newBuilder()
            .header("Authorization", "$AUTHENTICATION_HEADER_TYPE $token")
            .build()

        return chain.proceed(authenticatedRequest)
    }
}
