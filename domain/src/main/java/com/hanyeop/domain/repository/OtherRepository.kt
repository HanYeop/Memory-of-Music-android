package com.hanyeop.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot

interface OtherRepository {
    fun getRecommendation() : Task<QuerySnapshot>
}