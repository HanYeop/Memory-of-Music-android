package com.hanyeop.data.repository.music.remote

import com.hanyeop.data.model.music.MusicResponse

interface MusicRemoteDataSource {
    suspend fun getRemoteMusics(keyword: String): MusicResponse
}