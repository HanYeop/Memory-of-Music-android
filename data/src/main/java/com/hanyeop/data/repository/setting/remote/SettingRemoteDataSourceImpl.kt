package com.hanyeop.data.repository.setting.remote

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.hanyeop.data.model.setting.DataInquiry
import javax.inject.Inject

class SettingRemoteDataSourceImpl @Inject constructor(
    private val fireStore : FirebaseFirestore
) : SettingRemoteDataSource {
    override fun setInquiry(inquiry: DataInquiry): Task<Void> = fireStore.collection("inquiry").document().set(inquiry)
}