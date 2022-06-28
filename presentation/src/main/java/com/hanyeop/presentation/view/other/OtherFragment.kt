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
            essayAdapter.submitList(
                listOf(EssayResponse("","제목1","가수1","https://hanyeop.tistory.com/429"),
                    EssayResponse("","제목1","가수1"),
                    EssayResponse("","제목112","3가수1"),
                    EssayResponse("","제목13","가수31"),
                    EssayResponse("","제목1","가수31"),
                    EssayResponse("","제목123","가수31"),
                    EssayResponse("","제목13","가수1"))
            )
        }

        repeatOnStarted {
            otherViewModel.recommendationList.collect{
                recommendationAdapter.submitList(it)
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