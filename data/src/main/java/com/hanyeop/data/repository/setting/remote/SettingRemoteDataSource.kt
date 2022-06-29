package com.hanyeop.data.repository.setting.remote

import com.google.android.gms.tasks.Task
import com.hanyeop.data.model.setting.DataInquiry

interface SettingRemoteDataSource {
    fun setInquiry(inquiry: DataInquiry): Task<Void>
}