package com.hanyeop.mom.di

import com.hanyeop.data.repository.music.MusicRepositoryImpl
import com.hanyeop.data.repository.music.local.MusicLocalDataSource
import com.hanyeop.domain.repository.MusicRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMusicRepository(
        musicLocalDataSource: MusicLocalDataSource
    ): MusicRepository{
        return MusicRepositoryImpl(musicLocalDataSource)
    }
}