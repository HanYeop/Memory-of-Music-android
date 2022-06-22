package com.hanyeop.data.model.album

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "album_table")
data class AlbumEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    var image: String = "",
    var title: String = "",
    var artist: String = "",
    var genre: String = "",
    var trackList: String = "",
    var rating: Float = 0f,
    var summary: String = "",
    var content: String = "",
    var time: Long = System.currentTimeMillis()
)