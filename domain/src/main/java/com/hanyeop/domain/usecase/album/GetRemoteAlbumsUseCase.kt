package com.hanyeop.domain.usecase.album

import com.hanyeop.domain.repository.AlbumRepository
import javax.inject.Inject

class GetRemoteAlbumsUseCase @Inject constructor(private val albumRepository: AlbumRepository) {
    fun execute(keyword: String) = albumRepository.getRemoteAlbums(keyword)
}