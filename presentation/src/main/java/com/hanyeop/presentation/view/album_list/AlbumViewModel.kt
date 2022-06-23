package com.hanyeop.presentation.view.album_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanyeop.domain.model.album.Album
import com.hanyeop.domain.model.album.DomainAlbumResponse
import com.hanyeop.domain.usecase.album.GetAllAlbumCountUseCase
import com.hanyeop.domain.usecase.album.GetAllAlbumUseCase
import com.hanyeop.domain.usecase.album.GetRemoteAlbumsUseCase
import com.hanyeop.domain.usecase.album.InsertAlbumUseCase
import com.hanyeop.domain.utils.Result
import com.hanyeop.presentation.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val insertAlbumUseCase: InsertAlbumUseCase,
    private val getAllAlbumUseCase: GetAllAlbumUseCase,
    private val getRemoteAlbumsUseCase: GetRemoteAlbumsUseCase,
    private val getAllAlbumCountUseCase: GetAllAlbumCountUseCase
) : ViewModel() {

    val id: MutableStateFlow<Int> = MutableStateFlow(0)
    val image: MutableStateFlow<String> = MutableStateFlow("")
    val title: MutableStateFlow<String> = MutableStateFlow("")
    val artist: MutableStateFlow<String> = MutableStateFlow("")
    val genre: MutableStateFlow<String> = MutableStateFlow("장르")
    val trackList: MutableStateFlow<String> = MutableStateFlow("")
    val summary: MutableStateFlow<String> = MutableStateFlow("")
    val content: MutableStateFlow<String> = MutableStateFlow("")

    private val _inputErrorEvent = MutableSharedFlow<Int>()
    val inputErrorEvent = _inputErrorEvent.asSharedFlow()
    private val _inputSuccessEvent = MutableSharedFlow<Int>()
    val inputSuccessEvent = _inputSuccessEvent.asSharedFlow()

    private val _remoteAlbums: MutableStateFlow<Result<List<DomainAlbumResponse>>> = MutableStateFlow(Result.Uninitialized)
    val remoteAlbums get() = _remoteAlbums.asStateFlow()

    // 검색 결과 클릭 시 결과 정보 불러옴
    fun setAlbumInfo(albumInfo: DomainAlbumResponse){
        image.value = albumInfo.image
        title.value = albumInfo.title
        artist.value = albumInfo.artist
        genre.value = ""
        trackList.value = albumInfo.trackList
        summary.value = ""
        content.value = ""
    }

    // 장르 스피너 선택 결과
    fun setGenre(selected: String){
        genre.value = selected
    }

    fun insertAlbum(rating: Float){
        if(title.value.isNotBlank() && artist.value.isNotBlank()
            && summary.value.isNotBlank() && content.value.isNotBlank() && genre.value != "장르"){
            viewModelScope.launch(Dispatchers.IO) {
                insertAlbumUseCase.execute(
                    Album(
                        image = image.value,
                        title = title.value,
                        artist = artist.value,
                        genre = genre.value,
                        trackList = trackList.value,
                        rating = rating,
                        summary = summary.value,
                        content = content.value
                    )
                )
                _inputSuccessEvent.emit(R.string.insert_success)
            }
        }
        else{
            viewModelScope.launch(Dispatchers.IO) {
                _inputErrorEvent.emit(R.string.insert_error)
            }
        }
    }

    var albumList: StateFlow<Result<List<Album>>> =
        getAllAlbumUseCase.execute()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = Result.Uninitialized
            )

    var trackBoolean: MutableStateFlow<Boolean> = MutableStateFlow(false)
    fun hideTrackList(){
        trackBoolean.value = !trackBoolean.value
    }

    fun getRemoteAlbums(keyword: String){
        viewModelScope.launch(Dispatchers.IO) {
            getRemoteAlbumsUseCase.execute(keyword).collectLatest {
                _remoteAlbums.value = it
            }
        }
    }

    var albumCount: StateFlow<Result<Int>> =
        getAllAlbumCountUseCase.execute()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = Result.Uninitialized
            )
}