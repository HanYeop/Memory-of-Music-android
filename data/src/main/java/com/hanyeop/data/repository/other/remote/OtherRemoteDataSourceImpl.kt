package com.hanyeop.data.repository.other.remote

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import javax.inject.Inject

class OtherRemoteDataSourceImpl @Inject constructor(
    private val fireStore : FirebaseFirestore
) : OtherRemoteDataSource{
    override fun getRecommendation(): Task<QuerySnapshot>
        = fireStore.collection("recommendation").get()
}