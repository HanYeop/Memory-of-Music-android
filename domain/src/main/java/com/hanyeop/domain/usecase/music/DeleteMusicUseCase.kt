package com.hanyeop.domain.usecase.music

import com.hanyeop.domain.repository.MusicRepository
import javax.inject.Inject

class DeleteMusicUseCase @Inject constructor(private val musicRepository: MusicRepository) {
    fun execute(id : Int) = musicRepository.deleteMusic(id)
}