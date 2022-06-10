package com.hanyeop.data.repository.music.remote

import com.hanyeop.data.model.music.MusicResponse
import kotlinx.coroutines.flow.Flow

interface MusicRemoteDataSource {
    fun getRemoteMusics(keyword: String): Flow<MusicResponse>
}