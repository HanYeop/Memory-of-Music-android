package com.hanyeop.data.model.album

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
data class AlbumResponse(
    @field:Element(name = "channel")
    var channel: AlbumChannel? = null
)

@Root(name = "channel", strict = false)
data class AlbumChannel @JvmOverloads constructor(

    // 결과 없을 경우 고려 (required = false)
    @field:ElementList(inline = true, required = false)
    var itemList: List<AlbumItem>? = null
)

@Root(name = "item", strict = false)
data class AlbumItem @JvmOverloads constructor(
    @field:Element(name = "title_short")
    var title: String = "",

    @field:Element(name = "albumtrack")
    var album: AlbumTrack? = null,

    @field:Element(name = "albumartists")
    var artist: String? = null,

    @field:Element(name = "image")
    var image: String = ""
)

@Root(name = "albumtrack", strict = false)
data class AlbumTrack @JvmOverloads constructor(
    @field:Element(name = "tracklist")
    var trackList: String = ""
)
