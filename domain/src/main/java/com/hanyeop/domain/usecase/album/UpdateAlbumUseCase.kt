package com.hanyeop.domain.usecase.album

import com.hanyeop.domain.repository.AlbumRepository
import javax.inject.Inject

class UpdateAlbumUseCase @Inject constructor(private val albumRepository: AlbumRepository) {
    fun execute(id: Int, title: String, artist: String, genre: String, rating: Float, summary: String, content: String)
        = albumRepository.updateAlbum(id, title, artist, genre, rating, summary, content)
}