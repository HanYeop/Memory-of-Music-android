package com.hanyeop.mom.di

import com.google.firebase.firestore.FirebaseFirestore
import com.hanyeop.data.api.MusicApi
import com.hanyeop.data.repository.album.remote.AlbumRemoteDataSource
import com.hanyeop.data.repository.album.remote.AlbumRemoteDataSourceImpl
import com.hanyeop.data.repository.music.remote.MusicRemoteDataSource
import com.hanyeop.data.repository.music.remote.MusicRemoteDataSourceImpl
import com.hanyeop.data.repository.other.remote.OtherRemoteDataSource
import com.hanyeop.data.repository.other.remote.OtherRemoteDataSourceImpl
import com.hanyeop.data.repository.setting.remote.SettingRemoteDataSource
import com.hanyeop.data.repository.setting.remote.SettingRemoteDataSourceImpl
import com.hanyeop.mom.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {

    // Retrofit DI
    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
    }

    // MusicApi DI
    @Provides
    @Singleton
    fun provideMusicApiService(retrofit: Retrofit): MusicApi {
        return retrofit.create(MusicApi::class.java)
    }

    // MusicRemoteDataSource DI
    @Singleton
    @Provides
    fun provideMusicRemoteDataSource(musicApi: MusicApi): MusicRemoteDataSource {
        return MusicRemoteDataSourceImpl(musicApi)
    }

    // AlbumRemoteDataSource DI
    @Singleton
    @Provides
    fun provideAlbumRemoteDataSource(musicApi: MusicApi): AlbumRemoteDataSource {
        return AlbumRemoteDataSourceImpl(musicApi)
    }

    // OtherRemoteDataSource DI
    @Singleton
    @Provides
    fun provideOtherRemoteDataSource(fireStore : FirebaseFirestore): OtherRemoteDataSource {
        return OtherRemoteDataSourceImpl(fireStore)
    }

    // SettingRemoteDataSource DI
    @Singleton
    @Provides
    fun provideSettingRemoteDataSource(fireStore : FirebaseFirestore): SettingRemoteDataSource {
        return SettingRemoteDataSourceImpl(fireStore)
    }
}