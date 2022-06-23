package com.hanyeop.data.repository.album.local

import com.hanyeop.data.db.AlbumDao
import com.hanyeop.data.model.album.AlbumEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AlbumLocalDataSourceImpl @Inject constructor(private val albumDao: AlbumDao)
    : AlbumLocalDataSource {
    override fun insertAlbum(album: AlbumEntity) = albumDao.insertAlbum(album)
    override fun getAllAlbum(): Flow<List<AlbumEntity>> = albumDao.getAllAlbum()
    override fun deleteAlbum(id: Int) = albumDao.deleteAlbum(id)
    override fun updateAlbum(id: Int, title: String, artist: String, genre: String, rating: Float, summary: String, content: String)
        = albumDao.updateAlbum(id, title, artist, genre, rating, summary, content)
    override fun getAllAlbumCount(): Flow<Int> = albumDao.getAllAlbumCount()
}