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

    // 음악 기록 삭제
    @Query("DELETE FROM music_table WHERE id = :id")
    fun deleteMusic(id: Int)

    // 음악 기록 수정
    @Query("UPDATE music_table SET title = :title, artist = :artist, genre = :genre, rating = :rating, summary = :summary, content = :content WHERE id = :id")
    fun updateMusic(id: Int, title: String, artist: String, genre: String, rating: Float, summary: String, content: String)

    // 음악 기록 평점별 불러오기 (최신 순으로, 기본값)
    @Query("SELECT * FROM music_table WHERE rating BETWEEN :start AND :end ORDER BY time DESC")
    fun getAllMusicByRating(start: Float, end: Float): Flow<List<MusicEntity>>

    // 음악 기록 평점/장르별 불러오기 (최신 순으로, 기본값)
    @Query("SELECT * FROM music_table WHERE rating BETWEEN :start AND :end AND genre = :genre ORDER BY time DESC")
    fun getAllMusicByCategory(start: Float, end: Float, genre: String): Flow<List<MusicEntity>>

    // 음악 기록 개수 불러오기
    @Query("SELECT COUNT(*) FROM music_table")
    fun getAllMusicCount(): Flow<Int>

    // 모든 앨범 기록 삭제
    @Query("DELETE FROM music_table ")
    fun deleteAllMusic()
}