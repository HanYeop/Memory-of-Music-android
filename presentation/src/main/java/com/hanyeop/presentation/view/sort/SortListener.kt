package com.hanyeop.presentation.view.sort

interface SortListener {
    fun onTimeDescClicked()
    fun onTimeAscClicked()
    fun onTitleAscClicked()
    fun onTitleDescClicked()
    fun onRatingDescClicked()
    fun onRatingAscClicked()
}