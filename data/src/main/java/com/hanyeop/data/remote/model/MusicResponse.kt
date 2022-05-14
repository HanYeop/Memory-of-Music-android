package com.hanyeop.data.remote.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
data class MusicResponse(
    @field:Element(name = "channel")
    var channel: Channel? = null
)

@Root(name = "channel", strict = false)
data class Channel @JvmOverloads constructor(

    // 결과 없을 경우 고려 (required = false)
    @field:ElementList(inline = true, required = false)
    var itemList: List<Item>? = null
)

@Root(name = "item", strict = false)
data class Item @JvmOverloads constructor(
    @field:Element(name = "title")
    var title: String = "",

    @field:Element(name = "album")
    var album: Album? = null,

    @field:Element(name = "artist")
    var artist: Artist? = null
)

@Root(name = "album", strict = false)
data class Album @JvmOverloads constructor(
    @field:Element(name = "image")
    var image: String = ""
)

@Root(name = "artist", strict = false)
data class Artist @JvmOverloads constructor(
    @field:Element(name = "name")
    var name: String = ""
)
