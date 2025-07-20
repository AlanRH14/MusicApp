package com.example.musicapp.data.remote.repository

import com.example.musicapp.common.ApiMapper
import com.example.musicapp.data.model.request.LoginRequest
import com.example.musicapp.data.model.reponse.LoginResponse
import com.example.musicapp.data.model.request.RegisterRequest
import com.example.musicapp.domain.repository.AuthenticationRepository
import com.example.musicapp.common.Resource
import com.example.musicapp.data.local.database.entities.UserEntity
import com.example.musicapp.data.local.datasource.UserLocalDataSource
import com.example.musicapp.data.remote.datasource.RemoteAuthDataSource
import com.example.musicapp.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthenticationRepositoryImpl(
    private val remoteDataSource: RemoteAuthDataSource,
    private val localDataSource: UserLocalDataSource,
    private val apiLoginMapper: ApiMapper<LoginResponse, UserEntity>,
    private val apiUserMapper: ApiMapper<UserEntity, User>,
) : AuthenticationRepository {

    override fun login(loginRequest: LoginRequest): Flow<Resource<User>> = flow {
        emit(Resource.Loading)
        try {
            val response = remoteDataSource.login(loginRequest)
            val entity = apiLoginMapper.mapToDomain(apiDto = response)
            localDataSource.savaUser(user = entity)
            emit(Resource.Success(apiUserMapper.mapToDomain(apiDto = entity)))
        } catch (e: Exception) {
            emit(Resource.Error(message = "Error: ${e.message}"))
        }
    }

    override suspend fun register(registerRequest: RegisterRequest): Resource<User> {
        Resource.Loading
        return try {
            val response = remoteDataSource.register(registerRequest)
            val entity = apiLoginMapper.mapToDomain(apiDto = response)
            localDataSource.savaUser(user = entity)
            Resource.Success(apiUserMapper.mapToDomain(apiDto = entity))
        } catch (e: Exception) {
            Resource.Error(message = "Error: ${e.message}")
        }
    }
}