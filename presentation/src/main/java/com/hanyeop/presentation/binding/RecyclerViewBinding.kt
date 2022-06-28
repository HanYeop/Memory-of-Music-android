package com.hanyeop.presentation.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hanyeop.domain.model.album.DomainAlbumResponse
import com.hanyeop.domain.model.music.DomainMusicResponse
import com.hanyeop.domain.utils.Result
import com.hanyeop.presentation.view.album_list.search.AlbumSearchAdapter
import com.hanyeop.presentation.view.music_list.search.MusicSearchAdapter

object RecyclerViewBinding {

    // 검색 리사이클러뷰 아이템 바인딩
    @JvmStatic
    @BindingAdapter("submitList")
    fun bindSubmitList(view: RecyclerView, result: Result<*>) {
        if (result is Result.Success) {
            when (view.adapter) {
                is MusicSearchAdapter -> {
                    (view.adapter as ListAdapter<Any, *>).submitList(result.data as List<DomainMusicResponse>)
                }
                is AlbumSearchAdapter -> {
                    (view.adapter as ListAdapter<Any, *>).submitList(result.data as List<DomainAlbumResponse>)
                }
            }
        } else if (result is Result.Empty) {
            (view.adapter as ListAdapter<Any, *>).submitList(emptyList())
        }
    }
}