package com.example.musicapp.di

import com.example.musicapp.presentation.create_playlist.CreatePlaylistViewModel
import com.example.musicapp.presentation.home.HomeViewModel
import com.example.musicapp.presentation.login.LoginViewModel
import com.example.musicapp.presentation.onboarding.OnboardingViewModel
import com.example.musicapp.presentation.play_song.PlaySongViewModel
import com.example.musicapp.presentation.playlist.PlaylistViewModel
import com.example.musicapp.presentation.playlist_detail.PlaylistDetailViewModel
import com.example.musicapp.presentation.register.RegisterViewModel
import com.example.musicapp.presentation.splash.SplashViewModel
import com.example.musicapp.utils.QualifiersConstants
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { SplashViewModel(ioDispatcher = get(QualifiersConstants.DISPATCHERS_IO)) }
    viewModel { OnboardingViewModel(dataStoreHandle = get()) }
    viewModel { LoginViewModel(repository = get(), dataStoreHandle = get()) }
    viewModel { RegisterViewModel(repository = get()) }
    viewModel { HomeViewModel(repository = get()) }
    viewModel {
        PlaySongViewModel(
            mContext = get(),
            repository = get(),
            playlistRepository = get()
        )
    }
    viewModel { PlaylistViewModel(playlistRepository = get()) }
    viewModel { CreatePlaylistViewModel(playlistRepository = get()) }
    viewModel { PlaylistDetailViewModel(repository = get()) }
}