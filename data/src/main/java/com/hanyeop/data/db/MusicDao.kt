package com.hanyeop.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hanyeop.data.model.music.MusicEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MusicDao {

    // 음악 기록 추가
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMusic(music : MusicEntity)

    // 음악 기록 불러오기
    @Query("SELECT * FROM music_table")
    fun getAllMusics(): Flow<List<MusicEntity>>
}