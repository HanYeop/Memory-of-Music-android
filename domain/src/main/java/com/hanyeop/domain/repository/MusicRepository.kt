package com.hanyeop.domain.repository

import com.hanyeop.domain.model.music.DomainMusicResponse
import com.hanyeop.domain.model.music.Music
import com.hanyeop.domain.utils.Result
import kotlinx.coroutines.flow.Flow

interface MusicRepository {
    suspend fun insertMusic(music : Music)
    fun getAllMusic(): Flow<Result<List<Music>>>
    suspend fun getRemoteMusics(keyword: String): Flow<Result<List<DomainMusicResponse>>>
}