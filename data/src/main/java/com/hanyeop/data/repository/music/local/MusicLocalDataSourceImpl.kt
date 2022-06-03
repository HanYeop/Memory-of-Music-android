package com.hanyeop.data.repository.music.local

import com.hanyeop.data.db.MusicDao
import com.hanyeop.data.model.music.MusicEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MusicLocalDataSourceImpl @Inject constructor(private val musicDao: MusicDao)
    : MusicLocalDataSource {
    override suspend fun insertMusic(music: MusicEntity) = musicDao.insertMusic(music)
    override fun getAllMusics(): Flow<List<MusicEntity>> = musicDao.getAllMusics()
}