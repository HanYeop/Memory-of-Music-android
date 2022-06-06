package com.hanyeop.data.api

import com.hanyeop.data.model.music.MusicResponse
import com.hanyeop.data.utils.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path

interface MusicApi {
    @GET("{keyword}/?sr=song&display=10&key=$API_KEY&v=0.5")
    suspend fun getRemoteMusics(@Path("keyword") keyword: String) : MusicResponse
}