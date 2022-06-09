package com.hanyeop.presentation.view.music_list.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hanyeop.domain.model.music.DomainMusicResponse
import com.hanyeop.presentation.databinding.ItemMusicSearchBinding

class MusicSearchAdapter(private val listener: MusicSearchAdapterListener)
    : ListAdapter<DomainMusicResponse, MusicSearchAdapter.ViewHolder>(diffUtil){

    inner class ViewHolder(private val binding: ItemMusicSearchBinding): RecyclerView.ViewHolder(binding.root){
        init {
            binding.root.setOnClickListener {
                listener.onItemClicked(getItem(adapterPosition))
            }
        }
        fun bind(musicInfo: DomainMusicResponse){
            binding.musicInfo = musicInfo
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMusicSearchBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

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