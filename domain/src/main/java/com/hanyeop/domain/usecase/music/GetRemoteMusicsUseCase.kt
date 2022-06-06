package com.hanyeop.domain.usecase.music

import com.hanyeop.domain.repository.MusicRepository
import javax.inject.Inject

class GetRemoteMusicsUseCase @Inject constructor(private val musicRepository: MusicRepository) {
    suspend fun execute(keyword: String) = musicRepository.getRemoteMusics(keyword)
}