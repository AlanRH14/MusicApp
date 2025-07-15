package com.example.musicapp.utils

import android.util.Patterns.EMAIL_ADDRESS
import java.util.Locale

object ValidateFormat {
    fun String?.emailFormatValid(): Boolean =
        EMAIL_ADDRESS.matcher(this ?: "").matches()

    fun String.passwordFormatValid(): Boolean =
        true

    fun Long.toFormattedTime(): String {
        val duration = this / 1000
        val minutes = (duration / 60).toInt()
        val seconds = (duration % 60).toInt()
        return String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
    }
}
