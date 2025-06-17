package com.example.musicapp.data.mapper_impl.user

import com.example.musicapp.common.music.ApiMapper
import com.example.musicapp.data.model.UserDto
import com.example.musicapp.domain.model.User

class UserApiMapperImpl : ApiMapper<UserDto, User> {

    override fun mapToDomain(apiDto: UserDto): User {
        return User(
            id = apiDto.id ?: "",
            email = apiDto.email ?: "",
            name = apiDto.name ?: "",
            profilePicture = apiDto.profilePicture ?: "",
            createdAt = apiDto.updateAt ?: 0L,
            updateAt = apiDto.updateAt ?: 0L,
        )
    }
}