package com.example.musicapp.di

import com.example.musicapp.presentation.home.HomeViewModel
import com.example.musicapp.presentation.login.LoginViewModel
import com.example.musicapp.presentation.onboarding.OnboardingViewModel
import com.example.musicapp.presentation.register.RegisterViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { OnboardingViewModel() }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { HomeViewModel(get()) }
}