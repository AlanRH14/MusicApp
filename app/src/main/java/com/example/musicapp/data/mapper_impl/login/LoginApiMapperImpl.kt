package com.example.musicapp.data.mapper_impl.login

import com.example.musicapp.common.ApiMapper
import com.example.musicapp.data.model.UserDto
import com.example.musicapp.data.model.reponse.LoginResponse
import com.example.musicapp.domain.model.Login
import com.example.musicapp.domain.model.User

class LoginApiMapperImpl(
    private val apiUserMapper: ApiMapper<UserDto, User>
) : ApiMapper<LoginResponse, Login> {

    override fun mapToDomain(apiDto: LoginResponse): Login {
        return Login(
            token = apiDto.token ?: "",
            user = apiUserMapper.mapToDomain(apiDto.user ?: UserDto())
        )
    }
}