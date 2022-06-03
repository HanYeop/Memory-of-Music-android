package com.hanyeop.data.mapper

import com.hanyeop.data.model.music.MusicEntity
import com.hanyeop.domain.model.music.Music

// Domain -> Data
fun mapperToMusicEntity(music: Music): MusicEntity{
    return MusicEntity(
        image = music.image,
        title = music.title,
        album = music.album,
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
            album = it.album,
            artist = it.artist,
            rating = it.rating,
            summary = it.summary,
            content = it.content,
        )
    }
}