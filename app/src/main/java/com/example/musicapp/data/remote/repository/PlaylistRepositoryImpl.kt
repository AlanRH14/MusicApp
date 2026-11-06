package com.example.musicapp.data.remote.repository

import com.example.musicapp.common.Mapper
import com.example.musicapp.common.Resource
import com.example.musicapp.data.local.preferences.ConstantsPreferences
import com.example.musicapp.data.model.PlaylistDto
import com.example.musicapp.data.model.request.CreatePlaylistRequest
import com.example.musicapp.data.model.request.UpdatePlaylistSongRequest
import com.example.musicapp.data.remote.api.PlaylistApiService
import com.example.musicapp.data.remote.api.utils.safeApiCall
import com.example.musicapp.data.remote.api.utils.safeFlowApiCall
import com.example.musicapp.domain.exception.AuthException
import com.example.musicapp.domain.model.ActionMessage
import com.example.musicapp.domain.model.Playlist
import com.example.musicapp.domain.model.PlaylistData
import com.example.musicapp.domain.repository.DataStoreHandle
import com.example.musicapp.domain.repository.PlaylistRepository
import com.example.musicapp.utils.constants.AppConstants.AUTHENTICATION_HEADER_TYPE
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import kotlin.collections.map

class PlaylistRepositoryImpl(
    private val apiService: PlaylistApiService,
    private val dataStore: DataStoreHandle,
    private val apiPlaylistMapper: Mapper<PlaylistDto, Playlist>,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : PlaylistRepository {

    override fun getPlaylist(): Flow<Resource<List<Playlist>>> =
        safeFlowApiCall(
            ioDispatcher = ioDispatcher,
            apiCall = { apiService.getPlaylist() },
            transform = { playlistData ->
                playlistData.map { apiPlaylistMapper.mapTo(input = it) }
            }
        )

    override suspend fun createPlaylist(playlistData: PlaylistData): Resource<Playlist> =
        withContext(ioDispatcher) {
            safeApiCall(
                apiCall = {
                    apiService.createPlaylist(
                        playlistRequest = CreatePlaylistRequest(
                            name = playlistData.name,
                            description = playlistData.description,
                            coverImage = playlistData.coverImage,
                            songIds = playlistData.songIds
                        )
                    )
                },
                transform = { playlistData ->
                    apiPlaylistMapper.mapTo(input = playlistData)
                }
            )
        }

    override suspend fun addSongToPlaylist(
        playlistID: String,
        songID: String
    ): Resource<ActionMessage> =
        withContext(ioDispatcher) {
            safeApiCall(
                apiCall = {
                    apiService.addSongToPlaylist(
                        playlistId = playlistID,
                        request = UpdatePlaylistSongRequest(songIds = listOf(songID))
                    )
                },
                transform = { ActionMessage(message = "Song added to playlist successfully") }
            )
        }

    override suspend fun deleteSongFromPlaylist(
        playlistID: String,
        songID: String
    ): Resource<ActionMessage> = withContext(ioDispatcher) {
        safeApiCall(
            apiCall = {
                apiService.removeSongsFromPlaylist(
                    playlistId = playlistID,
                    request = UpdatePlaylistSongRequest(songIds = listOf(songID))
                )
            },
            transform = {
                ActionMessage(message = "Song deleted successfully")
            }
        )
    }

    override fun getPlaylistDetails(playlistID: String): Flow<Resource<Playlist>> =
        safeFlowApiCall(
            ioDispatcher = ioDispatcher,
            apiCall = { apiService.getPlaylistById(id = playlistID) },
            transform = { res -> apiPlaylistMapper.mapTo(input = res) }
        )
}
