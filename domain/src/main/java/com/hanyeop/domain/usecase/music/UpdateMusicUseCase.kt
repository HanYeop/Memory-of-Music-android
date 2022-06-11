package com.hanyeop.domain.usecase.music

import com.hanyeop.domain.repository.MusicRepository
import javax.inject.Inject

class UpdateMusicUseCase @Inject constructor(private val musicRepository: MusicRepository) {
    fun execute(id: Int, title: String, artist: String, rating: Float, summary: String, content: String)
        = musicRepository.updateMusic(id, title, artist, rating, summary, content)
}