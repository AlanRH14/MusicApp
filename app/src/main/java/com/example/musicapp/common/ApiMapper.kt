package com.example.musicapp.common

interface ApiMapper<Entity, Domain> {
    fun mapToDomain(apiDto: Entity): Domain
}