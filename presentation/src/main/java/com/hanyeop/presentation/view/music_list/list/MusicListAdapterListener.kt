package com.hanyeop.presentation.view.music_list.list

import com.hanyeop.domain.model.music.Music

interface MusicListAdapterListener {
    fun onItemClicked(music : Music)
    fun onOtherButtonClicked(music : Music)
}