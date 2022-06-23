package com.hanyeop.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hanyeop.data.model.album.AlbumEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbumDao {

    // 앨범 기록 추가
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlbum(album : AlbumEntity)

    // 앨범 기록 불러오기 (최신 순으로, 기본값)
    @Query("SELECT * FROM album_table ORDER BY time DESC")
    fun getAllAlbum(): Flow<List<AlbumEntity>>

    // 앨범 기록 삭제
    @Query("DELETE FROM album_table WHERE id = :id")
    fun deleteAlbum(id: Int)

    // 앨범 기록 수정
    @Query("UPDATE album_table SET title = :title, artist = :artist, genre = :genre, rating = :rating, summary = :summary, content = :content WHERE id = :id")
    fun updateAlbum(id: Int, title: String, artist: String, genre: String, rating: Float, summary: String, content: String)

//    // 음악 기록 평점별 불러오기 (최신 순으로, 기본값)
//    @Query("SELECT * FROM music_table WHERE rating BETWEEN :start AND :end ORDER BY time DESC")
//    fun getAllMusicByRating(start: Float, end: Float): Flow<List<MusicEntity>>
//
//    // 음악 기록 평점/장르별 불러오기 (최신 순으로, 기본값)
//    @Query("SELECT * FROM music_table WHERE rating BETWEEN :start AND :end AND genre = :genre ORDER BY time DESC")
//    fun getAllMusicByCategory(start: Float, end: Float, genre: String): Flow<List<MusicEntity>>

    // 음악 기록 개수 불러오기
    @Query("SELECT COUNT(*) FROM album_table")
    fun getAllAlbumCount(): Flow<Int>
}