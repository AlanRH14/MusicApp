package com.example.musicapp.presentation.play_song

import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.musicapp.R
import com.example.musicapp.domain.model.Playlist
import com.example.musicapp.presentation.common.widgets.ErrorScreen
import com.example.musicapp.presentation.common.widgets.LoadingScreen
import com.example.musicapp.presentation.play_song.mvi.PlaySongEffect
import com.example.musicapp.presentation.play_song.mvi.PlaySongUIEvent
import com.example.musicapp.presentation.play_song.widgets.PlaySongContent
import com.example.musicapp.presentation.play_song.widgets.PlaylistBottomSheet
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaySongScreen(
    navController: NavHostController,
    viewModel: PlaySongViewModel = koinViewModel(),
    songID: String,
) {
    val state by viewModel.state.collectAsState()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    var shouldShowSheet by remember { mutableStateOf(false) }
    val playlist by remember { mutableStateOf<List<Playlist>>(emptyList()) }

    LaunchedEffect(key1 = true) {
        viewModel.onEvent(PlaySongUIEvent.GetSongByID(songID = songID))

        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is PlaySongEffect.ShowErrorMessage -> {
                    Toast.makeText(navController.context, effect.message, Toast.LENGTH_SHORT).show()
                }

                is PlaySongEffect.NavigateToPlaylist -> {}

                is PlaySongEffect.ShowPlaylistSelection -> {
                    shouldShowSheet = true
                }
            }
        }
    }

    state.currentSong?.let {
        PlaySongContent(
            state = state,
            onEvent = viewModel::onEvent
        )
    }

    if (state.isLoading) {
        LoadingScreen()
    }

    if (!state.error.isNullOrEmpty()) {
        ErrorScreen(
            errorMessage = state.error ?: stringResource(R.string.unknown),
            primaryButton = stringResource(R.string.retry),
            onPrimaryButtonClicked = {},
        )
    }

    if (shouldShowSheet) {
        PlaylistBottomSheet(
            onDismissRequest = { shouldShowSheet = false },
            sheetState = sheetState,
            playlists = playlist,
            songID = songID,
            onEvent = viewModel::onEvent
        )
    }
}