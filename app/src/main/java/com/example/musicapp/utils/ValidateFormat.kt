package com.example.musicapp.utils

import android.util.Patterns.EMAIL_ADDRESS

fun String.emailFormatValid(): Boolean =
    EMAIL_ADDRESS.matcher(this).matches()

fun String.passwordFormatValid(): Boolean =
    true