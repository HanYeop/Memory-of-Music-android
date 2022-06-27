package com.hanyeop.presentation.view.other

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hanyeop.domain.model.other.RecommendationResponse
import com.hanyeop.presentation.databinding.ItemRecommendationBinding

class RecommendationAdapter()
    : ListAdapter<RecommendationResponse, RecommendationAdapter.ViewHolder>(diffUtil){

    inner class ViewHolder(private val binding: ItemRecommendationBinding): RecyclerView.ViewHolder(binding.root){
        init {

        }
        fun bind(recommendation: RecommendationResponse){
            binding.recommendation = recommendation
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecommendationBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<RecommendationResponse>(){
            override fun areItemsTheSame(oldItem: RecommendationResponse, newItem: RecommendationResponse): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: RecommendationResponse, newItem: RecommendationResponse): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    }
}