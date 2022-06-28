package com.hanyeop.domain.usecase.other

import com.hanyeop.domain.repository.OtherRepository
import javax.inject.Inject

class GetEssayUseCase @Inject constructor(private val otherRepository: OtherRepository) {
    fun execute() = otherRepository.getEssay()
}