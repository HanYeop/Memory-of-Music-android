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
    fun insertMusic(music : MusicEntity)

    // 음악 기록 불러오기 (최신 순으로, 기본값)
    @Query("SELECT * FROM music_table ORDER BY time DESC")
    fun getAllMusic(): Flow<List<MusicEntity>>
}