package com.example.musicapp.utils

import android.util.Patterns.EMAIL_ADDRESS

fun String?.emailFormatValid(): Boolean =
    this?.let {
        EMAIL_ADDRESS.matcher(this).matches()
    } ?: false

fun String.passwordFormatValid(): Boolean =
    true