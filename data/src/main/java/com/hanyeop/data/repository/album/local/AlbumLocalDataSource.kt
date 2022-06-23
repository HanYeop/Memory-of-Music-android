package com.hanyeop.data.repository.album.local

import com.hanyeop.data.model.album.AlbumEntity
import kotlinx.coroutines.flow.Flow

interface AlbumLocalDataSource {
    fun insertAlbum(album : AlbumEntity)
    fun getAllAlbum(): Flow<List<AlbumEntity>>
    fun deleteAlbum(id : Int)
    fun updateAlbum(id: Int, title: String, artist: String, genre: String, rating: Float, summary: String, content: String)
    fun getAllAlbumCount(): Flow<Int>
}