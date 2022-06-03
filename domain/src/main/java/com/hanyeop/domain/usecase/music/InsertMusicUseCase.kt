package com.hanyeop.domain.usecase.music

import com.hanyeop.domain.model.music.Music
import com.hanyeop.domain.repository.MusicRepository
import javax.inject.Inject

class InsertMusicUseCase @Inject constructor(private val musicRepository: MusicRepository) {
    suspend fun execute(music: Music) = musicRepository.insertMusic(music)
}