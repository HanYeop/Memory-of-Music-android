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

//    // 앨범 기록 수정
//    @Query("UPDATE music_table SET title = :title, artist = :artist, rating = :rating, summary = :summary, content = :content WHERE id = :id")
//    fun updateAlbum(id: Int, title: String, artist: String, rating: Float, summary: String, content: String)
}