package com.hanyeop.presentation.view.other.essay

import com.hanyeop.domain.model.other.EssayResponse

interface EssayAdapterListener {
    fun onItemClicked(essay : EssayResponse)
}