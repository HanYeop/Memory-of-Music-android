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
    fun updateAlbum(id: Int, title: String, artist: String, genre: String, rating: Float, summary: String, content: String)
    fun getAllAlbumByRating(start: Float, end: Float): Flow<Result<List<Album>>>
    fun getAllAlbumByCategory(start: Float, end: Float, genre: String): Flow<Result<List<Album>>>
    fun getAllAlbumCount(): Flow<Result<Int>>
    fun deleteAllAlbum()
}