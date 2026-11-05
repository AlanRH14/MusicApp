package com.example.musicapp.data.remote.repository

import com.example.musicapp.common.Mapper
import com.example.musicapp.data.model.request.LoginRequest
import com.example.musicapp.data.model.response.LoginResponse
import com.example.musicapp.data.model.request.RegisterRequest
import com.example.musicapp.domain.repository.AuthenticationRepository
import com.example.musicapp.common.Resource
import com.example.musicapp.data.local.database.entities.UserEntity
import com.example.musicapp.data.local.datasource.UserLocalDataSource
import com.example.musicapp.data.local.preferences.ConstantsPreferences
import com.example.musicapp.data.remote.api.AuthApiService
import com.example.musicapp.data.remote.api.extractErrorMessage
import com.example.musicapp.domain.exception.AuthException
import com.example.musicapp.domain.exception.NetworkException
import com.example.musicapp.domain.model.User
import com.example.musicapp.domain.repository.DataStoreHandle
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response

class AuthenticationRepositoryImpl(
    private val apiService: AuthApiService,
    private val localDataSource: UserLocalDataSource,
    private val dataStore: DataStoreHandle,
    private val userMapper: Mapper<LoginResponse, User>,
    private val userEntityMapper: Mapper<User, UserEntity>,
    private val ioDispatcher: CoroutineDispatcher
) : AuthenticationRepository {

    override suspend fun login(loginRequest: LoginRequest): Resource<User> =
        authenticate { apiService.login(loginRequest = loginRequest) }

    override suspend fun register(registerRequest: RegisterRequest): Resource<User> =
        authenticate { apiService.register(registerRequest = registerRequest) }

    private suspend fun authenticate(
        apiCall: suspend () -> Response<LoginResponse>
    ): Resource<User> = withContext(ioDispatcher) {
        try {
            val response = apiCall()

            if (!response.isSuccessful) {
                throw NetworkException.HttpException(
                    code = response.code(),
                    message = response.extractErrorMessage()
                )
            }

            val loginResponse = response.body() ?: throw NetworkException.EmptyBody()
            val token = loginResponse.token
            if (token.isNullOrEmpty()) {
                return@withContext Resource.Error(throwable = AuthException.NullTokenException())
            }

            dataStore.saveState(ConstantsPreferences.AuthTokenPreferences, token)

            val domainUser = userMapper.mapTo(input = loginResponse)
            localDataSource.saveUser(userEntityMapper.mapTo(domainUser))
            Resource.Success(domainUser)
        } catch (e: CancellationException) {
            throw e
        } catch (error: Exception) {
            Resource.Error(throwable = error)
        }
    }
}
