package com.hanyeop.mom.di

import com.hanyeop.data.repository.album.AlbumRepositoryImpl
import com.hanyeop.data.repository.album.local.AlbumLocalDataSource
import com.hanyeop.data.repository.album.remote.AlbumRemoteDataSource
import com.hanyeop.data.repository.music.MusicRepositoryImpl
import com.hanyeop.data.repository.music.local.MusicLocalDataSource
import com.hanyeop.data.repository.music.remote.MusicRemoteDataSource
import com.hanyeop.data.repository.other.OtherRepositoryImpl
import com.hanyeop.data.repository.other.remote.OtherRemoteDataSource
import com.hanyeop.data.repository.setting.SettingRepositoryImpl
import com.hanyeop.data.repository.setting.remote.SettingRemoteDataSource
import com.hanyeop.domain.repository.AlbumRepository
import com.hanyeop.domain.repository.MusicRepository
import com.hanyeop.domain.repository.OtherRepository
import com.hanyeop.domain.repository.SettingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    // MusicRepository DI
    @Provides
    @Singleton
    fun provideMusicRepository(
        musicLocalDataSource: MusicLocalDataSource,
        musicRemoteDataSource: MusicRemoteDataSource
    ): MusicRepository{
        return MusicRepositoryImpl(musicLocalDataSource, musicRemoteDataSource)
    }

    // AlbumRepository DI
    @Provides
    @Singleton
    fun provideAlbumRepository(
        albumLocalDataSource: AlbumLocalDataSource,
        albumRemoteDataSource: AlbumRemoteDataSource
    ): AlbumRepository{
        return AlbumRepositoryImpl(albumLocalDataSource, albumRemoteDataSource)
    }

    // OtherRepository DI
    @Provides
    @Singleton
    fun provideOtherRepository(
        otherRemoteDataSource: OtherRemoteDataSource
    ): OtherRepository {
        return OtherRepositoryImpl(otherRemoteDataSource)
    }

    // SettingRepository DI
    @Provides
    @Singleton
    fun provideSettingRepository(
        settingRemoteDataSource: SettingRemoteDataSource
    ): SettingRepository {
        return SettingRepositoryImpl(settingRemoteDataSource)
    }
}