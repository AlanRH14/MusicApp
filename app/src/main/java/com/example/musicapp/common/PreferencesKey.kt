package com.example.musicapp.common

interface PreferencesKey<T> {
    val defaultValue: T

    val key: String
}