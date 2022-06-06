package com.hanyeop.presentation.view.music_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hanyeop.domain.model.music.DomainMusicResponse
import com.hanyeop.presentation.databinding.ItemMusicSearchBinding

class MusicSearchAdapter
    : ListAdapter<DomainMusicResponse, MusicSearchAdapter.ViewHolder>(diffUtil){

    inner class ViewHolder(private val binding: ItemMusicSearchBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(musicInfo: DomainMusicResponse){
            binding.musicInfo = musicInfo
            binding.imageMusic.clipToOutline = true
        }
    }

    // 어떤 xml 으로 뷰 홀더를 생성할지 지정
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMusicSearchBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    // 뷰 홀더에 데이터 바인딩
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    // diffUtil 추가
    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<DomainMusicResponse>(){
            override fun areItemsTheSame(oldItem: DomainMusicResponse, newItem: DomainMusicResponse): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: DomainMusicResponse, newItem: DomainMusicResponse): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    }
}