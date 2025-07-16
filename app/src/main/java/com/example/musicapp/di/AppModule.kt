package com.example.musicapp.di

import com.example.musicapp.presentation.home.HomeViewModel
import com.example.musicapp.presentation.login.LoginViewModel
import com.example.musicapp.presentation.onboarding.OnboardingViewModel
import com.example.musicapp.presentation.play_song.PlaySongViewModel
import com.example.musicapp.presentation.playlist.PlaylistViewModel
import com.example.musicapp.presentation.register.RegisterViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    viewModel { OnboardingViewModel(get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { PlaySongViewModel(get(), get()) }
    viewModel { PlaylistViewModel(get()) }
}