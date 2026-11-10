package com.example.musicapp.data.remote.api.interceptors

import com.example.musicapp.domain.repository.SessionManager
import com.example.musicapp.utils.constants.NetworkConstants.AUTHENTICATION_HEADER_TYPE
import com.example.musicapp.utils.constants.NetworkConstants.AUTH_KEY
import com.example.musicapp.utils.constants.NetworkConstants.NO_AUTH_KEY
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val sessionManager: SessionManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        if (originalRequest.header(NO_AUTH_KEY) != null) {
            val requestWithoutHeader = originalRequest.newBuilder()
                .removeHeader(NO_AUTH_KEY)
                .build()
            return chain.proceed(requestWithoutHeader)
        }

        val token = sessionManager.getToken()
        if (token.isEmpty()) {
            return chain.proceed(originalRequest)
        }

        val authenticatedRequest = originalRequest.newBuilder()
            .header(AUTH_KEY, "$AUTHENTICATION_HEADER_TYPE $token")
            .build()

        return chain.proceed(authenticatedRequest)
    }
}
