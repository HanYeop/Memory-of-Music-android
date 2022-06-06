package com.hanyeop.presentation.view.music_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanyeop.domain.model.music.DomainMusicResponse
import com.hanyeop.domain.model.music.Music
import com.hanyeop.domain.usecase.music.GetAllMusicUseCase
import com.hanyeop.domain.usecase.music.GetRemoteMusicsUseCase
import com.hanyeop.domain.usecase.music.InsertMusicUseCase
import com.hanyeop.domain.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicViewModel @Inject constructor(
    private val insertMusicUseCase: InsertMusicUseCase,
    private val getAllMusicUseCase: GetAllMusicUseCase,
    private val getRemoteMusicsUseCase: GetRemoteMusicsUseCase
) : ViewModel() {

    private val _remoteMusics: MutableStateFlow<Result<List<DomainMusicResponse>>> = MutableStateFlow(Result.Uninitialized)
    val remoteMusics get() = _remoteMusics.asStateFlow()

    fun insertMusic(music: Music){
        viewModelScope.launch {
            insertMusicUseCase.execute(music)
        }
    }

    val musicList: StateFlow<Result<List<Music>>> =
        getAllMusicUseCase.execute()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = Result.Uninitialized
            )

    fun getRemoteMusics(keyword: String){
        viewModelScope.launch {
            getRemoteMusicsUseCase.execute(keyword).collect {
                _remoteMusics.value = it
            }
        }
    }
}