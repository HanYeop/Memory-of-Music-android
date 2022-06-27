package com.hanyeop.presentation.view.other

import com.hanyeop.domain.model.other.RecommendationResponse
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseFragmentMain
import com.hanyeop.presentation.databinding.FragmentOtherBinding

class OtherFragment : BaseFragmentMain<FragmentOtherBinding>(R.layout.fragment_other) {

    private val recommendationAdapter = RecommendationAdapter()

    override fun init() {
        binding.apply {
            otherRecyclerView.adapter = recommendationAdapter
        }
        recommendationAdapter.submitList(listOf(
            RecommendationResponse("0","test","hi"),
            RecommendationResponse("0","test2","hi2")
        ))
    }
}