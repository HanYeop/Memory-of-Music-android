package com.hanyeop.presentation.view.other.recommendation

import com.hanyeop.domain.model.other.RecommendationResponse

interface RecommendationAdapterListener {
    fun onItemClicked(recommendation : RecommendationResponse)
}