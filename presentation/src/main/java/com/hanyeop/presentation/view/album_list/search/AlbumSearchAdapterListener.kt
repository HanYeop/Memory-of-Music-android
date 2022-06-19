package com.hanyeop.presentation.view.album_list.search

import com.hanyeop.domain.model.album.DomainAlbumResponse

interface AlbumSearchAdapterListener {
    fun onItemClicked(albumInfo : DomainAlbumResponse)
}