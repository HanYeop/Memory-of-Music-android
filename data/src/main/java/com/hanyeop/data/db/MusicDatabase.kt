package com.hanyeop.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hanyeop.data.model.album.AlbumEntity
import com.hanyeop.data.model.music.MusicEntity

@Database(entities = [MusicEntity::class, AlbumEntity::class], version = 1, exportSchema = false)
abstract class MusicDatabase : RoomDatabase() {
    abstract fun MusicDao(): MusicDao
    abstract fun AlbumDao(): AlbumDao
}