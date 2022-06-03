package com.hanyeop.domain.repository

import com.hanyeop.domain.model.music.Music
import kotlinx.coroutines.flow.Flow

interface MusicRepository {
    suspend fun insertMusic(music : Music)
    fun getAllMusic(): Flow<List<Music>>
}