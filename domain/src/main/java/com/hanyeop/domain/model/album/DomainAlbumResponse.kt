package com.hanyeop.domain.model.album

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DomainAlbumResponse(
    val title: String = "",
    val artist: String = "",
    val image: String = "",
    val trackList: String = ""
): Parcelable
