package com.example.musicapp.data.mapper_impl.user

import com.example.musicapp.common.ApiMapper
import com.example.musicapp.data.local.database.entities.UserEntity
import com.example.musicapp.data.model.UserDto

class UserEntityMapperImpl : ApiMapper<UserDto, UserEntity> {

    override fun mapToDomain(apiDto: UserDto): UserEntity {
        return UserEntity(
            token = apiDto.id ?: "",
            name = apiDto.name ?: "",
            profilePicture = apiDto.profilePicture ?: ""
        )
    }
}