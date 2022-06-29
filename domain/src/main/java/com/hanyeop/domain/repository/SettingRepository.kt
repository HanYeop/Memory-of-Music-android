package com.hanyeop.domain.repository

import com.google.android.gms.tasks.Task
import com.hanyeop.domain.model.setting.DomainInquiry

interface SettingRepository {
    fun setInquiry(inquiry: DomainInquiry): Task<Void>
}