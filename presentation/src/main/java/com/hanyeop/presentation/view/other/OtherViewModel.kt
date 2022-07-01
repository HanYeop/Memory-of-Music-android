package com.hanyeop.presentation.view.other

import androidx.lifecycle.ViewModel
import com.hanyeop.domain.model.other.EssayResponse
import com.hanyeop.domain.model.other.RecommendationResponse
import com.hanyeop.domain.usecase.other.GetEssayUseCase
import com.hanyeop.domain.usecase.other.GetRecommendationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class OtherViewModel @Inject constructor(
    private val getRecommendationUseCase: GetRecommendationUseCase,
    private val getEssayUseCase: GetEssayUseCase
) : ViewModel() {

    private val _recommendationList: MutableStateFlow<List<RecommendationResponse>> = MutableStateFlow(listOf())
    val recommendationList get() = _recommendationList.asStateFlow()
    private val _essayList: MutableStateFlow<List<EssayResponse>> = MutableStateFlow(listOf())
    val essayList get() = _essayList.asStateFlow()
    private val _loading: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val loading get() = _loading.asStateFlow()

    fun getRecommendation() {
        _loading.value = true
        getRecommendationUseCase.execute()
            .addOnSuccessListener { snapshot ->
                val list = mutableListOf<RecommendationResponse>()
                for (item in snapshot.documents) {
                    item.toObject(RecommendationResponse::class.java).let {
                        list.add(it!!)
                    }
                }
                _recommendationList.value = list
                _loading.value = false
            }
    }

    fun getEssay() = getEssayUseCase.execute()
        .addOnSuccessListener { snapshot ->
            val list = mutableListOf<EssayResponse>()
            for(item in snapshot.documents){
                item.toObject(EssayResponse::class.java).let {
                    list.add(it!!)
                }
            }
            _essayList.value = list
        }

}