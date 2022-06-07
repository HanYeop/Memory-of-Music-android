package com.hanyeop.presentation.view.music_list.search

import com.hanyeop.domain.model.music.DomainMusicResponse

interface MusicSearchAdapterListener {
    fun onItemClicked(musicInfo : DomainMusicResponse)
}