package com.hanyeop.data.repository.music

import com.hanyeop.data.mapper.mapperToMusic
import com.hanyeop.data.mapper.mapperToMusicEntity
import com.hanyeop.data.repository.music.local.MusicLocalDataSource
import com.hanyeop.domain.model.music.Music
import com.hanyeop.domain.repository.MusicRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(
    private val musicLocalDataSource: MusicLocalDataSource
) : MusicRepository {
    override suspend fun insertMusic(music: Music) = musicLocalDataSource.insertMusic(mapperToMusicEntity(music))

    override fun getAllMusic(): Flow<List<Music>> = flow {
        musicLocalDataSource.getAllMusic().collect {
            emit(mapperToMusic(it))
        }
    }
}