package com.example.musicapp.data.remote.repository

import com.example.musicapp.common.Mapper
import com.example.musicapp.data.model.request.LoginRequest
import com.example.musicapp.data.model.reponse.LoginResponse
import com.example.musicapp.data.model.request.RegisterRequest
import com.example.musicapp.domain.repository.AuthenticationRepository
import com.example.musicapp.common.Resource
import com.example.musicapp.data.local.database.entities.UserEntity
import com.example.musicapp.data.local.datasource.UserLocalDataSource
import com.example.musicapp.data.remote.datasource.RemoteAuthDataSource
import com.example.musicapp.domain.exception.AuthException
import com.example.musicapp.domain.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class AuthenticationRepositoryImpl(
    private val remoteDataSource: RemoteAuthDataSource,
    private val localDataSource: UserLocalDataSource,
    private val userMapper: Mapper<LoginResponse, User>,
    private val userEntityMapper: Mapper<User, UserEntity>,
    private val ioDispatcher: CoroutineDispatcher
) : AuthenticationRepository {

    override suspend fun login(loginRequest: LoginRequest): Resource<User> =
        authenticate { remoteDataSource.login(loginRequest = loginRequest) }

    override suspend fun register(registerRequest: RegisterRequest): Resource<User> =
        authenticate { remoteDataSource.register(registerRequest = registerRequest) }

    private suspend fun authenticate(
        apiCall: suspend () -> LoginResponse
    ): Resource<User> = withContext(ioDispatcher) {
        try {
            val response = apiCall()

            val token = response.token
            if (token.isNullOrEmpty()) {
                return@withContext Resource.Error(throwable = AuthException.NullTokenException())
            }

            val domainUser = userMapper.mapTo(input = response)
            localDataSource.saveUser(userEntityMapper.mapTo(domainUser))
            Resource.Success(domainUser)
        } catch (error: Exception) {
            Resource.Error(throwable = error)
        }
    }
}
