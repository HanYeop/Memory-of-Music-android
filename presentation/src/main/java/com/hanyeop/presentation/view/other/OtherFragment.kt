package com.hanyeop.presentation.view.other

import androidx.fragment.app.activityViewModels
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseFragmentMain
import com.hanyeop.presentation.databinding.FragmentOtherBinding
import com.hanyeop.presentation.utils.repeatOnStarted
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class OtherFragment : BaseFragmentMain<FragmentOtherBinding>(R.layout.fragment_other) {

    private val otherViewModel by activityViewModels<OtherViewModel>()
    private val recommendationAdapter = RecommendationAdapter()

    override fun init() {
        binding.apply {
            otherRecyclerView.adapter = recommendationAdapter
            otherViewModel.getRecommendation()
        }

        repeatOnStarted {
            otherViewModel.recommendationList.collect{
                recommendationAdapter.submitList(it)
            }
        }
    }
}