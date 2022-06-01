package com.hanyeop.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hanyeop.data.model.music.MusicEntity

@Database(entities = [MusicEntity::class], version = 1, exportSchema = false)
abstract class MusicDatabase : RoomDatabase() {
    abstract fun MusicDao(): MusicDao
}