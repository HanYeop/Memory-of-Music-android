package com.hanyeop.mom.di

import android.content.Context
import androidx.room.Room
import com.hanyeop.data.db.MusicDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {

    // Database(Room) DI
    @Singleton
    @Provides
    fun provideMusicDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, MusicDatabase::class.java,"music_db")
            .fallbackToDestructiveMigration() // 버전 변경 시 기존 데이터 삭제
            .build()
}