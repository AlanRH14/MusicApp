package com.example.musicapp.di

import com.example.musicapp.common.music.ApiMapper
import com.example.musicapp.data.mapper_impl.login.LoginApiMapperImpl
import com.example.musicapp.data.mapper_impl.user.UserApiMapperImpl
import com.example.musicapp.data.model.UserDto
import com.example.musicapp.data.model.reponse.LoginResponse
import com.example.musicapp.domain.model.Login
import com.example.musicapp.domain.model.User
import org.koin.core.qualifier.named
import org.koin.dsl.module

val apiMapperModule = module {
    single<ApiMapper<UserDto, User>>(named("UserApiMapper")) { UserApiMapperImpl() }
    single<ApiMapper<LoginResponse, Login>>(named("LoginApiMapper")) { LoginApiMapperImpl(get(named("UserApiMapper"))) }
}