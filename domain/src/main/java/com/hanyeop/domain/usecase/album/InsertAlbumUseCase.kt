package com.hanyeop.domain.usecase.album

import com.hanyeop.domain.model.album.Album
import com.hanyeop.domain.repository.AlbumRepository
import javax.inject.Inject

class InsertAlbumUseCase @Inject constructor(private val albumRepository: AlbumRepository) {
    fun execute(album: Album) = albumRepository.insertAlbum(album)
}