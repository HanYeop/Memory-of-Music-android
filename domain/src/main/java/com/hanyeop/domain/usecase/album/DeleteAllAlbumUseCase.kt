package com.hanyeop.domain.usecase.album

import com.hanyeop.domain.repository.AlbumRepository
import javax.inject.Inject

class DeleteAllAlbumUseCase @Inject constructor(private val albumRepository: AlbumRepository) {
    fun execute() = albumRepository.deleteAllAlbum()
}