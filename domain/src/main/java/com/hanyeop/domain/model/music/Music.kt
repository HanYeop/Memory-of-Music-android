package com.hanyeop.domain.model.music

data class Music(
    var image: String = "",
    var title: String = "",
    var album: String = "",
    var artist: String = "",
    var rating: Float = 0f,
    var summary: String = "",
    var content: String = "",
) {
    var id : Int = 0
}
