package com.hanyeop.data.repository.music.remote

import com.hanyeop.data.api.MusicApi
import com.hanyeop.data.model.music.MusicResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MusicRemoteDataSourceImpl @Inject constructor(
    private val musicApi: MusicApi
): MusicRemoteDataSource{
    override fun getRemoteMusics(keyword: String): Flow<MusicResponse> = flow {
        emit(musicApi.getRemoteMusics(keyword))
    }
}