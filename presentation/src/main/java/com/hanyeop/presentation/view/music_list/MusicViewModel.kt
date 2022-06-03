package com.hanyeop.presentation.view.music_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanyeop.domain.model.music.Music
import com.hanyeop.domain.usecase.music.InsertMusicUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicViewModel @Inject constructor(
    private val insertMusicUseCase: InsertMusicUseCase
) : ViewModel() {

    fun insertMusic(music: Music){
        viewModelScope.launch {
            insertMusicUseCase.execute(music)
        }
    }
}