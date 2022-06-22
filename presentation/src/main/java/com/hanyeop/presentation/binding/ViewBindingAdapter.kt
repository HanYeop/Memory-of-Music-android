package com.hanyeop.presentation.binding

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.hanyeop.domain.utils.Result
import com.hanyeop.presentation.R
import com.hanyeop.presentation.utils.timeFormatter


object ViewBindingAdapter {

    // 음악 검색 이미지 바인딩
    @BindingAdapter("searchImage")
    @JvmStatic
    fun ImageView.setSearchImage (imageUrl: Any){
        Glide.with(this.context)
            .load(imageUrl)
            .override(R.dimen.search_image_size * 2,R.dimen.search_image_size * 2)
            .placeholder(R.drawable.image_loading)
            .into(this)
        this.clipToOutline = true
    }

    // 음악 medium 이미지 바인딩
    @BindingAdapter("mediumImage")
    @JvmStatic
    fun ImageView.setMediumImage (imageUrl: Any){
        Glide.with(this.context)
            .load(imageUrl)
            .override(R.dimen.medium_image_size * 2,R.dimen.medium_image_size * 2)
            .placeholder(R.drawable.image_loading)
            .into(this)
        this.clipToOutline = true
    }

    // 음악 리스트 이미지 바인딩
    @BindingAdapter("musicListImage")
    @JvmStatic
    fun ImageView.setMusicListImage (imageUrl: Any){
        Glide.with(this.context)
            .load(imageUrl)
            .override(R.dimen.list_image_size * 2,R.dimen.list_image_size * 2)
            .placeholder(R.drawable.image_loading)
            .into(this)
        this.clipToOutline = true
    }

    // 음악 large 이미지 바인딩
    @BindingAdapter("largeImage")
    @JvmStatic
    fun ImageView.setMusicLargeImage (imageUrl: Any){
        Glide.with(this.context)
            .load(imageUrl)
            .override(R.dimen.large_image_size * 2,R.dimen.large_image_size * 2)
            .placeholder(R.drawable.image_loading)
            .into(this)
        this.clipToOutline = true
    }

    // 로딩 상태 표시
    @JvmStatic
    @BindingAdapter("isLoading")
    fun View.setIsLoading(result: Result<*>) {
        this.isVisible = result is Result.Loading
    }

    // 데이터 없을 때 이미지 표시
    @JvmStatic
    @BindingAdapter("isEmpty")
    fun View.setEmptyImage(result: Result<*>) {
        if(result is Result.Success) {
            this.isVisible = result.data == 0
        }
    }

    // 에러 메세지 표시
    @JvmStatic
    @BindingAdapter("toast")
    fun View.bindToast(result: Result<*>) {
        if (result is Result.Error) {
            if (result.isNetworkError) {
                Toast.makeText(this.context, R.string.error_network, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this.context, R.string.error_unknown, Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 시간 표시
    @JvmStatic
    @BindingAdapter("time")
    fun TextView.setTime(time: Long) {
        this.text = timeFormatter(time)
    }

    // 한줄평 표시
    @JvmStatic
    @BindingAdapter("summary")
    fun TextView.setSummary(summary: String) {
        this.text = String.format(resources.getString(R.string.binding_summary), summary)
    }

    // 현재 필터 표시
    @JvmStatic
    @BindingAdapter("filterGenre", "filterStart", "filterEnd", "filterSort")
    fun TextView.setFilter(genre: String, start: Float, end: Float, sort: Int) {
        val curSort = resources.getStringArray(R.array.sort)
        this.text = "$genre / $start ~ $end / ${curSort[sort]}"
    }

    // 아이템 개수 표시
    @JvmStatic
    @BindingAdapter("itemCount")
    fun TextView.setItemCount(result: Result<*>) {
        if(result is Result.Success){
            this.text = result.data.toString()
        } else{
            this.text = "0"
        }
    }
}