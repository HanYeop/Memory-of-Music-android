package com.hanyeop.domain.usecase.other

import com.hanyeop.domain.repository.OtherRepository
import javax.inject.Inject

class GetRecommendationUseCase @Inject constructor(private val otherRepository: OtherRepository) {
    fun execute() = otherRepository.getRecommendation()
}