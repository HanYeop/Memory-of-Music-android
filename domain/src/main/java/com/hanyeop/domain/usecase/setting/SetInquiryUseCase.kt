package com.hanyeop.domain.usecase.setting

import com.hanyeop.domain.model.setting.DomainInquiry
import com.hanyeop.domain.repository.SettingRepository
import javax.inject.Inject

class SetInquiryUseCase @Inject constructor(private val settingRepository: SettingRepository) {
    fun execute(inquiry: DomainInquiry) = settingRepository.setInquiry(inquiry)
}