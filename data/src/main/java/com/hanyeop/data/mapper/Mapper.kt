package com.hanyeop.data.mapper

import com.hanyeop.data.model.music.MusicEntity
import com.hanyeop.data.model.music.MusicResponse
import com.hanyeop.domain.model.music.DomainMusicResponse
import com.hanyeop.domain.model.music.Music

// Domain -> Data (id, time 은 자동 생성, 받지 않는다.)
fun mapperToMusicEntity(music: Music): MusicEntity{
    return MusicEntity(
        image = music.image,
        title = music.title,
        artist = music.artist,
        rating = music.rating,
        summary = music.summary,
        content = music.content
    )
}

// Data -> Domain
fun mapperToMusic(musics: List<MusicEntity>): List<Music>{
    return musics.toList().map {
        Music(
            id = it.id,
            image = it.image,
            title = it.title,
            artist = it.artist,
            rating = it.rating,
            summary = it.summary,
            content = it.content,
            time = it.time
        )
    }
}

// Data -> Domain
fun mapperToMusicResponse(MusicResponses: MusicResponse): List<DomainMusicResponse>{
    return MusicResponses.channel!!.itemList!!.map {
        val title = it.title.replace("&nbsp;"," ").replace("&amp;","&")
        DomainMusicResponse(
            title = title,
            artist = it.artist!!.name,
            image = it.album!!.image
        )
    }
}