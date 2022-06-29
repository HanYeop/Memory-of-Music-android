package com.hanyeop.data.repository.setting

import com.google.android.gms.tasks.Task
import com.hanyeop.data.mapper.mapperToDataInquiry
import com.hanyeop.data.repository.setting.remote.SettingRemoteDataSource
import com.hanyeop.domain.model.setting.DomainInquiry
import com.hanyeop.domain.repository.SettingRepository
import javax.inject.Inject

class SettingRepositoryImpl @Inject constructor(
    private val settingRemoteDataSource: SettingRemoteDataSource
) : SettingRepository {
    override fun setInquiry(inquiry: DomainInquiry): Task<Void>
        = settingRemoteDataSource.setInquiry(mapperToDataInquiry(inquiry))
}