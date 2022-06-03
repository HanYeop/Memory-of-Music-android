package com.hanyeop.data.model.music

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "music_table")
data class MusicEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    var image: String = "",
    var title: String = "",
    var album: String = "",
    var artist: String = "",
    var rating: Float = 0f,
    var summary: String = "",
    var content: String = "",
)