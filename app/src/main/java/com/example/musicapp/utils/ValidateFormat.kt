package com.example.musicapp.utils

import android.util.Patterns.EMAIL_ADDRESS

fun String?.emailFormatValid(): Boolean =
    EMAIL_ADDRESS.matcher(this ?: "").matches()

fun String.passwordFormatValid(): Boolean =
    true

fun formattedTime(millis: Long): String {
    val duration = millis / 1000
    val minutes = millis * 60 % 60
    val seconds = duration % 60

    return String.format("%02d:%02d", minutes, seconds)
}