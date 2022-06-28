package com.hanyeop.data.repository.other

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import com.hanyeop.data.repository.other.remote.OtherRemoteDataSource
import com.hanyeop.domain.repository.OtherRepository
import javax.inject.Inject

class OtherRepositoryImpl @Inject constructor(
    private val otherRemoteDataSource: OtherRemoteDataSource
) : OtherRepository{
    override fun getRecommendation(): Task<QuerySnapshot> = otherRemoteDataSource.getRecommendation()
    override fun getEssay(): Task<QuerySnapshot> = otherRemoteDataSource.getEssay()
}