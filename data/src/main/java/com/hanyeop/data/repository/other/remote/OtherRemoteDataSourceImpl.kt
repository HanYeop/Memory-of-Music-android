package com.hanyeop.data.repository.other.remote

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import javax.inject.Inject

class OtherRemoteDataSourceImpl @Inject constructor(
    private val fireStore : FirebaseFirestore
) : OtherRemoteDataSource{
    override fun getRecommendation(): Task<QuerySnapshot>
        = fireStore.collection("recommendation").orderBy("id", Query.Direction.DESCENDING).get()
    override fun getEssay(): Task<QuerySnapshot>
        = fireStore.collection("essay").orderBy("id", Query.Direction.DESCENDING).get()
}