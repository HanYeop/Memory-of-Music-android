package com.hanyeop.presentation.view.album_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanyeop.domain.model.album.Album
import com.hanyeop.domain.model.album.DomainAlbumResponse
import com.hanyeop.domain.usecase.album.*
import com.hanyeop.domain.utils.Result
import com.hanyeop.presentation.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val insertAlbumUseCase: InsertAlbumUseCase,
    private val getAllAlbumUseCase: GetAllAlbumUseCase,
    private val getRemoteAlbumsUseCase: GetRemoteAlbumsUseCase,
) : ViewModel() {

    private val _remoteAlbums: MutableStateFlow<Result<List<DomainAlbumResponse>>> = MutableStateFlow(Result.Uninitialized)
    val remoteAlbums get() = _remoteAlbums.asStateFlow()

//    // TEST
//    fun insertTest(){
//        for(i in 0 until 5){
//            viewModelScope.launch(Dispatchers.IO) {
//                insertAlbumUseCase.execute(
//                    Album(
//                        image = "",
//                        title = "abcd",
//                        artist = "하이",
//                        trackList = "1. test \n2. 하이",
//                        rating = i.toFloat(),
//                        summary = "테스트",
//                        content = "설명"
//                    )
//                )
//                _inputSuccessEvent.emit(R.string.insert_success)
//            }
//        }
//    }

    fun getRemoteAlbums(keyword: String){
        viewModelScope.launch(Dispatchers.IO) {
            getRemoteAlbumsUseCase.execute(keyword).collectLatest {
                Log.d("test5", "getRemoteAlbums: $it")
                _remoteAlbums.value = it
            }
        }
    }
}