package com.hanyeop.domain.repository

import com.hanyeop.domain.model.album.Album
import com.hanyeop.domain.model.album.DomainAlbumResponse
import com.hanyeop.domain.utils.Result
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {
    fun insertAlbum(album : Album)
    fun getAllAlbum(): Flow<Result<List<Album>>>
    fun getRemoteAlbums(keyword: String): Flow<Result<List<DomainAlbumResponse>>>
    fun deleteAlbum(id : Int)
}