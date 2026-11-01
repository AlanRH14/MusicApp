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
    private val userEntityMapper: Mapper<UserEntity, UserEntity>,
    private val ioDispatcher: CoroutineDispatcher
) : AuthenticationRepository {

    override suspend fun login(loginRequest: LoginRequest): Resource<User> =
        withContext(ioDispatcher) {
            try {
                val response = remoteDataSource.login(loginRequest)
                val entity = userMapper.mapTo(apiDto = response)
                if (entity.token.isNullOrEmpty()) {
                    return@withContext Resource.Error(throwable = AuthException.NullTokenException())
                }

                localDataSource.saveUser(user = entity)
                Resource.Success(userEntityMapper.mapTo(apiDto = entity))
            } catch (e: Exception) {
                Resource.Error(throwable = e)
            }
        }

    override suspend fun register(registerRequest: RegisterRequest): Resource<User> =
        withContext(ioDispatcher) {
            try {
                val response = remoteDataSource.register(registerRequest)
                val entity = userMapper.mapTo(apiDto = response)
                if (entity.token.isNullOrEmpty()) {
                    return@withContext Resource.Error(throwable = AuthException.NullTokenException())
                }

                localDataSource.saveUser(user = entity)
                Resource.Success(userEntityMapper.mapTo(apiDto = entity))
            } catch (e: Exception) {
                Resource.Error(throwable = e)
            }
        }

    private suspend fun authenticate(
        apiCall: suspend () -> LoginResponse
    ): Resource<User> = withContext(ioDispatcher) {
        val response = apiCall()

        val token = response.token
        if (token.isNullOrEmpty()) {
            return@withContext Resource.Error(AuthException.NullTokenException)
        }

        val domainUser = userMapper.mapTo(aresponse)
    }
}
