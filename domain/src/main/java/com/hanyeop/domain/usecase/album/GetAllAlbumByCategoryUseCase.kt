package com.hanyeop.domain.usecase.album

import com.hanyeop.domain.repository.AlbumRepository
import javax.inject.Inject

class GetAllAlbumByCategoryUseCase @Inject constructor(private val albumRepository: AlbumRepository) {
    fun execute(start: Float, end: Float, genre: String) = albumRepository.getAllAlbumByCategory(start, end, genre)
}