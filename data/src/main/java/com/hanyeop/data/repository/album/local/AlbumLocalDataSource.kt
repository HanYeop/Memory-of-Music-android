package com.hanyeop.data.repository.album.local

import com.hanyeop.data.model.album.AlbumEntity
import kotlinx.coroutines.flow.Flow

interface AlbumLocalDataSource {
    fun insertAlbum(album : AlbumEntity)
    fun getAllAlbum(): Flow<List<AlbumEntity>>
    fun deleteAlbum(id : Int)
}