package com.hanyeop.data.repository.music.local

import com.hanyeop.data.model.music.MusicEntity
import kotlinx.coroutines.flow.Flow

interface MusicLocalDataSource {
    fun insertMusic(music : MusicEntity)
    fun getAllMusic(): Flow<List<MusicEntity>>
    fun deleteMusic(id : Int)
    fun updateMusic(music : MusicEntity)
}