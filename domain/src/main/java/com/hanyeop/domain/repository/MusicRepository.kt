package com.hanyeop.domain.repository

import com.hanyeop.domain.model.music.DomainMusicResponse
import com.hanyeop.domain.model.music.Music
import com.hanyeop.domain.utils.Result
import kotlinx.coroutines.flow.Flow

interface MusicRepository {
    fun insertMusic(music : Music)
    fun getAllMusic(): Flow<Result<List<Music>>>
    fun getRemoteMusics(keyword: String): Flow<Result<List<DomainMusicResponse>>>
    fun deleteMusic(id : Int)
    fun updateMusic(id: Int, title: String, artist: String, genre: String, rating: Float, summary: String, content: String)
    fun getAllMusicByRating(start: Float, end: Float): Flow<Result<List<Music>>>
    fun getAllMusicByCategory(start: Float, end: Float, genre: String): Flow<Result<List<Music>>>
    fun getAllMusicCount(): Flow<Result<Int>>
}