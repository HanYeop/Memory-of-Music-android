package com.hanyeop.data.db

import androidx.room.*
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

    // 음악 기록 삭제
    @Query("DELETE FROM music_table WHERE id = :id")
    fun deleteMusic(id: Int)
}