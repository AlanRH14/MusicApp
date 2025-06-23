package com.example.musicapp.data.mapper_impl.user

import com.example.musicapp.common.ApiMapper
import com.example.musicapp.data.local.database.entities.UserEntity
import com.example.musicapp.data.model.reponse.LoginResponse

class UserEntityMapperImpl : ApiMapper<LoginResponse, UserEntity> {

    override fun mapToDomain(apiDto: LoginResponse): UserEntity {
        return UserEntity(
            token = apiDto.token ?: "",
            id = apiDto.user?.id ?: "",
            email = apiDto.user?.email ?: "",
            name = apiDto.user?.name ?: "",
            profilePicture = apiDto.user?.profilePicture ?: "",
            createdAt = apiDto.user?.updateAt ?: 0L,
            updateAt = apiDto.user?.updateAt ?: 0L,
        )
    }
}