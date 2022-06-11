package com.hanyeop.domain.usecase.music

import com.hanyeop.domain.model.music.Music
import com.hanyeop.domain.repository.MusicRepository
import javax.inject.Inject

class UpdateMusicUseCase @Inject constructor(private val musicRepository: MusicRepository) {
    fun execute(music: Music) = musicRepository.updateMusic(music)
}