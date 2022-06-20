package com.hanyeop.domain.usecase.music

import com.hanyeop.domain.repository.MusicRepository
import javax.inject.Inject

class GetAllMusicByRatingUseCase @Inject constructor(private val musicRepository: MusicRepository) {
    fun execute(start: Float, end: Float) = musicRepository.getAllMusicByRating(start, end)
}