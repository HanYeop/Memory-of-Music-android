package com.hanyeop.domain.usecase.album

import com.hanyeop.domain.repository.AlbumRepository
import javax.inject.Inject

class GetAllAlbumByRatingUseCase @Inject constructor(private val albumRepository: AlbumRepository) {
    fun execute(start: Float, end: Float) = albumRepository.getAllAlbumByRating(start, end)
}