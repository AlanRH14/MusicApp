package com.example.musicapp.common

interface Mapper<ApiDto, Domain> {
    fun mapTo(apiDto: ApiDto): Domain
}
