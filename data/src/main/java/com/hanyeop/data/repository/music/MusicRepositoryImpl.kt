package com.hanyeop.data.repository.music

import com.hanyeop.data.mapper.mapperToMusic
import com.hanyeop.data.mapper.mapperToMusicEntity
import com.hanyeop.data.repository.music.local.MusicLocalDataSource
import com.hanyeop.domain.model.music.Music
import com.hanyeop.domain.repository.MusicRepository
import com.hanyeop.domain.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(
    private val musicLocalDataSource: MusicLocalDataSource
) : MusicRepository {
    override suspend fun insertMusic(music: Music) = musicLocalDataSource.insertMusic(mapperToMusicEntity(music))

    override fun getAllMusic(): Flow<Result<List<Music>>> = flow {
        emit(Result.Loading)
        musicLocalDataSource.getAllMusic().collect {
            if(it.isEmpty()){
                emit(Result.Empty)
            }else{
                emit(Result.Success(mapperToMusic(it)))
            }
        }
    }.catch { e ->
        emit(Result.Error(e))
    }
}