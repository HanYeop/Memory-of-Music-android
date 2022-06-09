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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicViewModel @Inject constructor(
    private val insertMusicUseCase: InsertMusicUseCase,
    private val getAllMusicUseCase: GetAllMusicUseCase,
    private val getRemoteMusicsUseCase: GetRemoteMusicsUseCase
) : ViewModel() {

    val image: MutableStateFlow<String> = MutableStateFlow("")
    val title: MutableStateFlow<String> = MutableStateFlow("")
    val artist: MutableStateFlow<String> = MutableStateFlow("")
    val summary: MutableStateFlow<String> = MutableStateFlow("")
    val content: MutableStateFlow<String> = MutableStateFlow("")

    private val _inputErrorEvent = MutableSharedFlow<String>()
    val inputErrorEvent = _inputErrorEvent.asSharedFlow()
    private val _inputSuccessEvent = MutableSharedFlow<String>()
    val inputSuccessEvent = _inputSuccessEvent.asSharedFlow()

    private val _remoteMusics: MutableStateFlow<Result<List<DomainMusicResponse>>> = MutableStateFlow(Result.Uninitialized)
    val remoteMusics get() = _remoteMusics.asStateFlow()

    fun setMusicInfo(musicInfo: DomainMusicResponse){
        image.value = musicInfo.image
        title.value = musicInfo.title
        artist.value = musicInfo.artist
        summary.value = ""
        content.value = ""
    }

    fun insertMusic(){
        if(title.value.isNotBlank() && artist.value.isNotBlank() && summary.value.isNotBlank() && content.value.isNotBlank()){
            viewModelScope.launch(Dispatchers.IO) {
                insertMusicUseCase.execute(
                    Music(
                        image = image.value,
                        title = title.value,
                        artist = artist.value,
                        rating = 5.0f,
                        summary = summary.value,
                        content = content.value
                    )
                )
                _inputSuccessEvent.emit("음악이 등록되었습니다.")
            }
        }else{
            viewModelScope.launch(Dispatchers.IO) {
                _inputErrorEvent.emit("입력을 다시 확인해주세요.")
            }
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
        viewModelScope.launch(Dispatchers.IO) {
            getRemoteMusicsUseCase.execute(keyword).collectLatest {
                _remoteMusics.value = it
            }
        }
    }
}