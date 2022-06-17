package com.hanyeop.data.repository.album.remote

import com.hanyeop.data.model.album.AlbumResponse
import kotlinx.coroutines.flow.Flow

interface AlbumRemoteDataSource {
    fun getRemoteAlbums(keyword: String): Flow<AlbumResponse>
}