package com.example.musicapp.common

interface PreferencesKey<T> {
    val key: String

    val defaultValue: Any
}