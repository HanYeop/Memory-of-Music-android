package com.hanyeop.data.mapper

import com.hanyeop.data.model.album.AlbumEntity
import com.hanyeop.data.model.album.AlbumResponse
import com.hanyeop.domain.model.album.Album
import com.hanyeop.domain.model.album.DomainAlbumResponse

// Domain -> Data (id, time 은 자동 생성, 받지 않는다.)
fun mapperToAlbumEntity(album: Album): AlbumEntity {
    return AlbumEntity(
        image = album.image,
        title = album.title,
        artist = album.artist,
        trackList = album.trackList,
        rating = album.rating,
        summary = album.summary,
        content = album.content
    )
}

// Data -> Domain
fun mapperToAlbum(albums: List<AlbumEntity>): List<Album>{
    return albums.toList().map {
        Album(
            id = it.id,
            image = it.image,
            title = it.title,
            artist = it.artist,
            trackList = it.trackList,
            rating = it.rating,
            summary = it.summary,
            content = it.content,
            time = it.time
        )
    }
}

// Data -> Domain
fun mapperToAlbumResponse(albumResponses: AlbumResponse): List<DomainAlbumResponse>{
    return albumResponses.channel!!.itemList!!.map {
        val title = it.title.replace("&nbsp;"," ").replace("&amp;","&")
        val trackList = it.album!!.trackList.replace("&nbsp;"," ").replace("&amp;","&").replace("/", "\n")
            .replace("[Disc 1]","").replace("[Disc 2]","")
        DomainAlbumResponse(
            title = title,
            artist = it.artist.toString(),
            image = it.image,
            trackList = trackList
        )
    }
}