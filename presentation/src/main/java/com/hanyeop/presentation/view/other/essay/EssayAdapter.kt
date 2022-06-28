package com.hanyeop.presentation.view.other.essay

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hanyeop.domain.model.other.EssayResponse
import com.hanyeop.presentation.databinding.ItemEssayBinding

class EssayAdapter(private val listener: EssayAdapterListener)
    : ListAdapter<EssayResponse, EssayAdapter.ViewHolder>(diffUtil){

    inner class ViewHolder(private val binding: ItemEssayBinding): RecyclerView.ViewHolder(binding.root){
        init {
            binding.root.setOnClickListener {
                listener.onItemClicked(getItem(adapterPosition))
            }
        }
        fun bind(essay: EssayResponse){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemEssayBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<EssayResponse>(){
            override fun areItemsTheSame(oldItem: EssayResponse, newItem: EssayResponse): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: EssayResponse, newItem: EssayResponse): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    }
}