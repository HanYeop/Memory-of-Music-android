package com.hanyeop.data.repository.music.local

import com.hanyeop.data.model.music.MusicEntity
import kotlinx.coroutines.flow.Flow

interface MusicLocalDataSource {
    fun insertMusic(music : MusicEntity)
    fun getAllMusic(): Flow<List<MusicEntity>>
    fun deleteMusic(id : Int)
    fun updateMusic(id: Int, title: String, artist: String, genre: String, rating: Float, summary: String, content: String)
    fun getAllMusicByRating(start: Float, end: Float): Flow<List<MusicEntity>>
}