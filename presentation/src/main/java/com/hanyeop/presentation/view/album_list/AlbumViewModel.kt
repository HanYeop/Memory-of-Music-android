package com.hanyeop.presentation.view.album_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.play.core.internal.t
import com.hanyeop.domain.model.album.DomainAlbumResponse
import com.hanyeop.domain.usecase.album.*
import com.hanyeop.domain.utils.Result
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

    val id: MutableStateFlow<Int> = MutableStateFlow(0)
    val image: MutableStateFlow<String> = MutableStateFlow("")
    val title: MutableStateFlow<String> = MutableStateFlow("")
    val artist: MutableStateFlow<String> = MutableStateFlow("")
    val trackList: MutableStateFlow<String> = MutableStateFlow("")
    val summary: MutableStateFlow<String> = MutableStateFlow("")
    val content: MutableStateFlow<String> = MutableStateFlow("")

    private val _remoteAlbums: MutableStateFlow<Result<List<DomainAlbumResponse>>> = MutableStateFlow(Result.Uninitialized)
    val remoteAlbums get() = _remoteAlbums.asStateFlow()

    fun setAlbumInfo(albumInfo: DomainAlbumResponse){
        image.value = albumInfo.image
        title.value = albumInfo.title
        artist.value = albumInfo.artist
        trackList.value = albumInfo.trackList
        summary.value = ""
        content.value = ""
    }

    var trackBoolean: MutableStateFlow<Boolean> = MutableStateFlow(false)
    fun hideTrackList(){
        trackBoolean.value = !trackBoolean.value
    }

    fun getRemoteAlbums(keyword: String){
        viewModelScope.launch(Dispatchers.IO) {
            getRemoteAlbumsUseCase.execute(keyword).collectLatest {
                Log.d("test5", "getRemoteAlbums: $it")
                _remoteAlbums.value = it
            }
        }
    }
}