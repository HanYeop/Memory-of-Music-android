package com.hanyeop.presentation.view.music_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanyeop.domain.model.music.Music
import com.hanyeop.domain.usecase.music.GetAllMusicUseCase
import com.hanyeop.domain.usecase.music.InsertMusicUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicViewModel @Inject constructor(
    private val insertMusicUseCase: InsertMusicUseCase,
    private val getAllMusicUseCase: GetAllMusicUseCase
) : ViewModel() {

    fun insertMusic(music: Music){
        viewModelScope.launch {
            insertMusicUseCase.execute(music)
        }
    }

    val musicList: StateFlow<List<Music>> =
        getAllMusicUseCase.execute()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = listOf()
            )
}