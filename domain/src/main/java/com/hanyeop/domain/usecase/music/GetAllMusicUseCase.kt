package com.hanyeop.domain.usecase.music

import com.hanyeop.domain.repository.MusicRepository
import javax.inject.Inject

class GetAllMusicUseCase @Inject constructor(private val musicRepository: MusicRepository) {
    fun execute() = musicRepository.getAllMusic()
}