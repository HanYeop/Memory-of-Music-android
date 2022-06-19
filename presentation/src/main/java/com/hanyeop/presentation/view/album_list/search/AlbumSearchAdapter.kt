package com.hanyeop.presentation.view.album_list.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hanyeop.domain.model.album.DomainAlbumResponse
import com.hanyeop.presentation.databinding.ItemAlbumSearchBinding

class AlbumSearchAdapter(private val listener: AlbumSearchAdapterListener)
    : ListAdapter<DomainAlbumResponse, AlbumSearchAdapter.ViewHolder>(diffUtil){

    inner class ViewHolder(private val binding: ItemAlbumSearchBinding): RecyclerView.ViewHolder(binding.root){
        init {
            binding.root.setOnClickListener {
                listener.onItemClicked(getItem(adapterPosition))
            }
        }
        fun bind(albumInfo: DomainAlbumResponse){
            binding.albumInfo = albumInfo
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAlbumSearchBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<DomainAlbumResponse>(){
            override fun areItemsTheSame(oldItem: DomainAlbumResponse, newItem: DomainAlbumResponse): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: DomainAlbumResponse, newItem: DomainAlbumResponse): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    }
}