package com.hanyeop.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hanyeop.data.model.music.MusicEntity

@Dao
interface MusicDao {

    // 음악 기록 추가
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMusic(music : MusicEntity)

    @Query("SELECT * FROM music_table")
    fun getAllMusics(): LiveData<List<MusicEntity>>
}