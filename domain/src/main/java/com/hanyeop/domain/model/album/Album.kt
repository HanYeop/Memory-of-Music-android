package com.hanyeop.domain.model.album

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Album (
    var id : Int = 0,
    var image: String = "",
    var title: String = "",
    var artist: String = "",
    var trackList: String = "",
    var rating: Float = 0f,
    var summary: String = "",
    var content: String = "",
    var time: Long = System.currentTimeMillis()
): Parcelable