package com.hanyeop.domain.model.music

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Music(
    var id : Int = 0,
    var image: String = "",
    var title: String = "",
    var artist: String = "",
    var genre: String = "",
    var rating: Float = 0f,
    var summary: String = "",
    var content: String = "",
    var time: Long = System.currentTimeMillis()
): Parcelable
