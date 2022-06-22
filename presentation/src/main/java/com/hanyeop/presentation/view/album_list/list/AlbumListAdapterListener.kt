package com.hanyeop.presentation.view.album_list.list

import com.hanyeop.domain.model.album.Album

interface AlbumListAdapterListener {
    fun onItemClicked(album : Album)
    fun onOtherButtonClicked(album : Album)
}