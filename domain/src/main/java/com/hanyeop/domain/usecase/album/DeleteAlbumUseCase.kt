package com.hanyeop.domain.usecase.album

import com.hanyeop.domain.repository.AlbumRepository
import javax.inject.Inject

class DeleteAlbumUseCase @Inject constructor(private val albumRepository: AlbumRepository) {
    fun execute(id : Int) = albumRepository.deleteAlbum(id)
}