package com.hanyeop.presentation.view.music_list.insert

import com.hanyeop.domain.model.music.Music

interface MusicInsertDialogListener {
    fun onOkButtonClicked(music: Music)
}