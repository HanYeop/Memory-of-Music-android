package com.hanyeop.presentation.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ViewBindingAdapter {

    // 음악 검색 이미지 바인딩
    @BindingAdapter("searchImage")
    @JvmStatic
    fun ImageView.setImage (imageUrl: Any){
        Glide.with(this.context)
            .load(imageUrl)
            .override(100,100)
            .into(this)
    }
}