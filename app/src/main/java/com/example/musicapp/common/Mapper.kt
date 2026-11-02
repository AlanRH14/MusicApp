package com.example.musicapp.common

interface Mapper<Input, Out> {
    fun mapTo(input: Input): Out
}
