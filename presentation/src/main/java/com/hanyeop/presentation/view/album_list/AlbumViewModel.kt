package com.hanyeop.presentation.view.album_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanyeop.domain.model.album.Album
import com.hanyeop.domain.model.album.DomainAlbumResponse
import com.hanyeop.domain.usecase.album.*
import com.hanyeop.domain.utils.Result
import com.hanyeop.presentation.R
import com.hanyeop.presentation.utils.TIME_DESC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val insertAlbumUseCase: InsertAlbumUseCase,
    private val getAllAlbumUseCase: GetAllAlbumUseCase,
    private val getAllAlbumByRatingUseCase: GetAllAlbumByRatingUseCase,
    private val getAllAlbumByCategoryUseCase: GetAllAlbumByCategoryUseCase,
    private val getRemoteAlbumsUseCase: GetRemoteAlbumsUseCase,
    private val deleteAlbumUseCase: DeleteAlbumUseCase,
    private val updateAlbumUseCase: UpdateAlbumUseCase,
    private val getAllAlbumCountUseCase: GetAllAlbumCountUseCase,
    private val deleteAllAlbumUseCase: DeleteAllAlbumUseCase
) : ViewModel() {

    val id: MutableStateFlow<Int> = MutableStateFlow(0)
    val image: MutableStateFlow<String> = MutableStateFlow("")
    val title: MutableStateFlow<String> = MutableStateFlow("")
    val artist: MutableStateFlow<String> = MutableStateFlow("")
    val genre: MutableStateFlow<String> = MutableStateFlow("장르")
    val trackList: MutableStateFlow<String> = MutableStateFlow("")
    val summary: MutableStateFlow<String> = MutableStateFlow("")
    val content: MutableStateFlow<String> = MutableStateFlow("")

    val filterGenre: MutableStateFlow<String> = MutableStateFlow("전체")
    val filterStart: MutableStateFlow<Float> = MutableStateFlow(0.0f)
    val filterEnd: MutableStateFlow<Float> = MutableStateFlow(5.0f)
    val filterSort: MutableStateFlow<Int> = MutableStateFlow(0)

    private val _inputErrorMsg = MutableSharedFlow<Int>()
    val inputErrorMsg = _inputErrorMsg.asSharedFlow()
    private val _inputSuccessEvent = MutableSharedFlow<String>()
    val inputSuccessEvent = _inputSuccessEvent.asSharedFlow()
    private val _insertSuccessMsg = MutableSharedFlow<Int>()
    val insertSuccessMsg = _insertSuccessMsg.asSharedFlow()

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

    // 수정 버튼 클릭 시 기존의 정보 불러옴
    fun setAlbum(album: Album){
        id.value = album.id
        image.value = album.image
        title.value = album.title
        artist.value = album.artist
        genre.value = album.genre
        trackList.value = album.trackList
        summary.value = album.summary
        content.value = album.content
    }

    // 직접 추가시 모든 정보 초기화
    fun initAlbumInfo(){
        image.value = ""
        title.value = ""
        artist.value = ""
        genre.value = ""
        trackList.value = ""
        summary.value = ""
        content.value = ""
    }

    // 장르 스피너 선택 결과
    fun setGenre(selected: String){
        genre.value = selected
    }

    fun setFilterSort(type: Int){
        filterSort.value = type
    }

    private fun setFilterAll(genre: String, start: Float, end: Float, type: Int){
        filterGenre.value = genre
        filterStart.value = start
        filterEnd.value = end
        filterSort.value = type
    }

    fun test(){
        viewModelScope.launch(Dispatchers.IO) {
            for(i in 0 until 5) {
                insertAlbumUseCase.execute(
                    Album(
                        image = "",
                        title = i.toString(),
                        artist = "ab",
                        genre = "힙합",
                        trackList = "",
                        rating = i.toFloat(),
                        summary = "테스트",
                        content = "입니다 $i"
                    )
                )
            }
        }
    }

    fun insertAlbum(rating: Float){
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
            _insertSuccessMsg.emit(R.string.insert_success)
        }
    }

    var albumList: StateFlow<Result<List<Album>>> =
        getAllAlbumUseCase.execute()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = Result.Uninitialized
            )

    fun resetAlbumList(){
        albumList = getAllAlbumUseCase.execute()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = Result.Uninitialized
            )
        setFilterAll("전체", 0.0f, 5.0f, TIME_DESC)
    }

    fun changeAlbumList(start: Float, end: Float, genre: String){
        if(genre == "전체") {
            albumList =
                getAllAlbumByRatingUseCase.execute(start, end)
                    .stateIn(
                        scope = viewModelScope,
                        started = SharingStarted.WhileSubscribed(5000),
                        initialValue = Result.Uninitialized
                    )
        }
        else{
            albumList =
                getAllAlbumByCategoryUseCase.execute(start, end, genre)
                    .stateIn(
                        scope = viewModelScope,
                        started = SharingStarted.WhileSubscribed(5000),
                        initialValue = Result.Uninitialized
                    )
        }
        setFilterAll(genre, start, end, filterSort.value)
    }

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

    fun deleteAlbum(id : Int){
        viewModelScope.launch(Dispatchers.IO) {
            deleteAlbumUseCase.execute(id)
        }
    }

    fun updateAlbum(rating: Float){
        viewModelScope.launch(Dispatchers.IO) {
            updateAlbumUseCase.execute(id.value,title.value,artist.value,genre.value,rating,summary.value,content.value)
            _insertSuccessMsg.emit(R.string.update_success)
        }
    }

    var albumCount: StateFlow<Result<Int>> =
        getAllAlbumCountUseCase.execute()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = Result.Uninitialized
            )

    fun deleteAllAlbum(){
        viewModelScope.launch(Dispatchers.IO) {
            deleteAllAlbumUseCase.execute()
        }
    }

    fun inputCheck(){
        viewModelScope.launch(Dispatchers.IO) {
            when {
                title.value.isBlank() -> {
                    _inputErrorMsg.emit(R.string.error_title)
                }
                artist.value.isBlank() -> {
                    _inputErrorMsg.emit(R.string.error_artist)
                }
                summary.value.isBlank() ->{
                    _inputErrorMsg.emit(R.string.error_summary)
                }
                content.value.isBlank() ->{
                    _inputErrorMsg.emit(R.string.error_content)
                }
                genre.value == "장르" ->{
                    _inputErrorMsg.emit(R.string.error_genre)
                }else -> {
                _inputSuccessEvent.emit("검사 성공")
                }
            }
        }
    }
}