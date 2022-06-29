package com.hanyeop.presentation.view.other

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.activityViewModels
import com.hanyeop.domain.model.other.EssayResponse
import com.hanyeop.domain.model.other.RecommendationResponse
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseFragmentMain
import com.hanyeop.presentation.databinding.FragmentOtherBinding
import com.hanyeop.presentation.utils.repeatOnStarted
import com.hanyeop.presentation.view.other.essay.EssayAdapter
import com.hanyeop.presentation.view.other.essay.EssayAdapterListener
import com.hanyeop.presentation.view.other.recommendation.RecommendationAdapter
import com.hanyeop.presentation.view.other.recommendation.RecommendationAdapterListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OtherFragment
    : BaseFragmentMain<FragmentOtherBinding>(R.layout.fragment_other)
    , RecommendationAdapterListener, EssayAdapterListener {

    private val otherViewModel by activityViewModels<OtherViewModel>()
    private val recommendationAdapter = RecommendationAdapter(this)
    private val essayAdapter = EssayAdapter(this)

    override fun init() {
        binding.apply {
            recommendationRecyclerView.adapter = recommendationAdapter
            otherViewModel.getRecommendation()

            essayRecyclerView.adapter = essayAdapter
            otherViewModel.getEssay()
        }
        initViewModelCallback()
    }

    private fun initViewModelCallback(){
        repeatOnStarted {
            otherViewModel.recommendationList.collect{
                recommendationAdapter.submitList(it)
            }
        }
        repeatOnStarted {
            otherViewModel.essayList.collect{
                essayAdapter.submitList(it)
            }
        }
    }

    override fun onItemClicked(recommendation: RecommendationResponse) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query=${recommendation.artist} ${recommendation.title}"))
        requireContext().startActivity(intent)
    }

    override fun onItemClicked(essay: EssayResponse) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(essay.uri))
        requireContext().startActivity(intent)
    }
}