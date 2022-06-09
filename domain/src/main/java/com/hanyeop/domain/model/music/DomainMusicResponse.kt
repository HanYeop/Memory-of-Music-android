package com.hanyeop.domain.model.music

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DomainMusicResponse(
    val title: String = "",
    val artist: String = "",
    val image: String = ""
): Parcelable
