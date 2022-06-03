package com.hanyeop.domain.model.music

data class Music(
    var id : Int = 0,
    var image: String = "",
    var title: String = "",
    var album: String = "",
    var artist: String = "",
    var rating: Float = 0f,
    var summary: String = "",
    var content: String = "",
)
