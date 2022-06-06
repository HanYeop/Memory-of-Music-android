package com.hanyeop.presentation.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hanyeop.domain.model.music.Music
import com.hanyeop.domain.utils.Result
import com.hanyeop.presentation.view.music_list.MusicListAdapter

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("submitList")
    fun bindSubmitList(view: RecyclerView, result: Result<*>) {
        if (result is Result.Success) {
            when (view.adapter) {
                is MusicListAdapter -> {
                    (view.adapter as ListAdapter<Any, *>).submitList(result.data as List<Music>)
                }
            }
        } else if (result is Result.Empty) {
            (view.adapter as ListAdapter<Any, *>).submitList(emptyList())
        }
    }
}