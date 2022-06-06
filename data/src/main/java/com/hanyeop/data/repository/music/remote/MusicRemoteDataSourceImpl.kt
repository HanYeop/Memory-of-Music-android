package com.hanyeop.data.repository.music.remote

import com.hanyeop.data.api.MusicApi
import com.hanyeop.data.model.music.MusicResponse
import javax.inject.Inject

class MusicRemoteDataSourceImpl @Inject constructor(
    private val musicApi: MusicApi
): MusicRemoteDataSource{
    override suspend fun getRemoteMusics(keyword: String): MusicResponse = musicApi.getRemoteMusics(keyword)
}