package com.hanyeop.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hanyeop.domain.model.music.Music
import com.hanyeop.presentation.databinding.ItemMusicListBinding

class MusicAdapter
    : ListAdapter<Music, MusicAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemMusicListBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(music: Music){
            binding.music = music
        }
    }

    // 어떤 xml 으로 뷰 홀더를 생성할지 지정
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMusicListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    // 뷰 홀더에 데이터 바인딩
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    // diffUtil 추가
    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<Music>(){
            override fun areItemsTheSame(oldItem: Music, newItem: Music): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Music, newItem: Music): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    }
}