package com.hanyeop.mom.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    // Firestore DI
    @Provides
    @Singleton
    fun provideFirebaseStore() : FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }
}