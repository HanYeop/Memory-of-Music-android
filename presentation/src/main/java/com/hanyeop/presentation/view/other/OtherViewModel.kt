package com.hanyeop.presentation.view.other

import androidx.lifecycle.ViewModel
import com.hanyeop.domain.model.other.RecommendationResponse
import com.hanyeop.domain.usecase.other.GetRecommendationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class OtherViewModel @Inject constructor(
    private val getRecommendationUseCase: GetRecommendationUseCase
) : ViewModel() {

    private val _recommendationList: MutableStateFlow<List<RecommendationResponse>> = MutableStateFlow(listOf())
    val recommendationList get() = _recommendationList.asStateFlow()

    fun getRecommendation() = getRecommendationUseCase.execute()
        .addOnSuccessListener { snapshot ->
            val list = mutableListOf<RecommendationResponse>()
            for(item in snapshot.documents){
                item.toObject(RecommendationResponse::class.java).let {
                    list.add(it!!)
                }
            }
            list.reverse()
            _recommendationList.value = list
        }
}