package com.hanyeop.data.repository.other.remote

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot

interface OtherRemoteDataSource {
    fun getRecommendation() : Task<QuerySnapshot>
}