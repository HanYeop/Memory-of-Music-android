package com.hanyeop.presentation.view.music_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanyeop.domain.model.music.DomainMusicResponse
import com.hanyeop.domain.model.music.Music
import com.hanyeop.domain.usecase.music.*
import com.hanyeop.domain.utils.Result
import com.hanyeop.presentation.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicViewModel @Inject constructor(
    private val insertMusicUseCase: InsertMusicUseCase,
    private val getAllMusicUseCase: GetAllMusicUseCase,
    private val getAllMusicByRatingUseCase: GetAllMusicByRatingUseCase,
    private val getRemoteMusicsUseCase: GetRemoteMusicsUseCase,
    private val deleteMusicUseCase: DeleteMusicUseCase,
    private val updateMusicUseCase: UpdateMusicUseCase
) : ViewModel() {

    val id: MutableStateFlow<Int> = MutableStateFlow(0)
    val image: MutableStateFlow<String> = MutableStateFlow("")
    val title: MutableStateFlow<String> = MutableStateFlow("")
    val artist: MutableStateFlow<String> = MutableStateFlow("")
    val summary: MutableStateFlow<String> = MutableStateFlow("")
    val content: MutableStateFlow<String> = MutableStateFlow("")

    private val _inputErrorEvent = MutableSharedFlow<Int>()
    val inputErrorEvent = _inputErrorEvent.asSharedFlow()
    private val _inputSuccessEvent = MutableSharedFlow<Int>()
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

    fun setMusic(music: Music){
        id.value = music.id
        image.value = music.image
        title.value = music.title
        artist.value = music.artist
        summary.value = music.summary
        content.value = music.content
    }

    fun initMusicInfo(){
        image.value = ""
        title.value = ""
        artist.value = ""
        summary.value = ""
        content.value = ""
    }

    fun insertMusic(rating: Float){
        if(title.value.isNotBlank() && artist.value.isNotBlank() && summary.value.isNotBlank() && content.value.isNotBlank()){
            viewModelScope.launch(Dispatchers.IO) {
                insertMusicUseCase.execute(
                    Music(
                        image = image.value,
                        title = title.value,
                        artist = artist.value,
                        rating = rating,
                        summary = summary.value,
                        content = content.value
                    )
                )
                _inputSuccessEvent.emit(R.string.insert_success)
            }
        }else{
            viewModelScope.launch(Dispatchers.IO) {
                _inputErrorEvent.emit(R.string.insert_error)
            }
        }
    }

    var musicList: StateFlow<Result<List<Music>>> =
        getAllMusicUseCase.execute()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = Result.Uninitialized
            )

    fun abc(start: Float, end: Float){
        musicList =
            getAllMusicByRatingUseCase.execute(start, end)
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5000),
                    initialValue = Result.Uninitialized
                )

    }

    fun getRemoteMusics(keyword: String){
        viewModelScope.launch(Dispatchers.IO) {
            getRemoteMusicsUseCase.execute(keyword).collectLatest {
                _remoteMusics.value = it
            }
        }
    }

    fun deleteMusic(id : Int){
        viewModelScope.launch(Dispatchers.IO) {
            deleteMusicUseCase.execute(id)
        }
    }

    fun updateMusic(rating: Float){
        if(title.value.isNotBlank() && artist.value.isNotBlank() && summary.value.isNotBlank() && content.value.isNotBlank()){
            viewModelScope.launch(Dispatchers.IO) {
                updateMusicUseCase.execute(id.value,title.value,artist.value,rating,summary.value,content.value)
                _inputSuccessEvent.emit(R.string.update_success)
            }
        }else{
            viewModelScope.launch(Dispatchers.IO) {
                _inputErrorEvent.emit(R.string.insert_error)
            }
        }
    }
}