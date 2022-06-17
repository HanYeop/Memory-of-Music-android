package com.hanyeop.data.repository.album.remote

import com.hanyeop.data.api.MusicApi
import com.hanyeop.data.model.album.AlbumResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AlbumRemoteDataSourceImpl @Inject constructor(
    private val musicApi: MusicApi
): AlbumRemoteDataSource {
    override fun getRemoteAlbums(keyword: String): Flow<AlbumResponse> = flow {
        emit(musicApi.getRemoteAlbums(keyword))
    }
}