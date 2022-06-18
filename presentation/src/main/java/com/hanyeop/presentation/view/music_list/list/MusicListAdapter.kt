package com.hanyeop.presentation.view.music_list.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hanyeop.domain.model.music.Music
import com.hanyeop.presentation.databinding.ItemMusicBriefBinding
import com.hanyeop.presentation.databinding.ItemMusicListBinding
import com.hanyeop.presentation.utils.*

class MusicListAdapter(private val listener: MusicListAdapterListener)
    : ListAdapter<Music, RecyclerView.ViewHolder>(diffUtil),Filterable {

    private var originalList = arrayListOf<Music>()
    private var filterList = arrayListOf<Music>()

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                return FilterResults().apply {
                    values = if (constraint.isNullOrEmpty()){
                        originalList
                    }else{
                        originalList.filter { it.title.lowercase().contains(constraint.toString()) }
                    }
                }
            }
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterList = results?.values as ArrayList<Music>
                submitList(results?.values as? List<Music>)
            }
        }
    }

    fun order(type: Int){
        // default (최신순)
        originalList.sortBy { it.time }
        originalList.reverse()
        filterList.sortBy { it.time }
        filterList.reverse()

        when(type){
            TIME_ASC ->{
                originalList.sortBy { it.time }
                filterList.sortBy { it.time }
            }
            TITLE_ASC ->{
                originalList.sortBy { it.title.lowercase() }
                filterList.sortBy { it.title.lowercase() }
            }
            TITLE_DESC ->{
                originalList.sortBy { it.title.lowercase() }
                originalList.reverse()
                filterList.sortBy { it.title.lowercase() }
                filterList.reverse()
            }
            RATING_DESC ->{
                originalList.sortBy { it.rating }
                originalList.reverse()
                filterList.sortBy { it.rating }
                filterList.reverse()
            }
            RATING_ASC ->{
                originalList.sortBy { it.rating }
                filterList.sortBy { it.rating }
            }
        }
        submitList(filterList)
        notifyDataSetChanged()
    }

    fun setItem(items: List<Music>){
        originalList.clear()
        originalList.addAll(items)
        submitList(originalList)
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).id.toLong()
    }

    inner class AllViewHolder(private val binding: ItemMusicListBinding):RecyclerView.ViewHolder(binding.root){
        init {
            binding.apply {
                root.setOnClickListener {
                    listener.onItemClicked(getItem(adapterPosition))
                }
                root.setOnLongClickListener {
                    listener.onOtherButtonClicked(getItem(adapterPosition))
                    false
                }
                imageOther.setOnClickListener {
                    listener.onOtherButtonClicked(getItem(adapterPosition))
                }
            }
        }
        fun bind(music: Music){
            binding.music = music
        }
    }

    inner class BriefViewHolder(private val binding: ItemMusicBriefBinding):RecyclerView.ViewHolder(binding.root){
        init {
            binding.apply {
                root.setOnClickListener {
                    listener.onItemClicked(getItem(adapterPosition))
                }
                root.setOnLongClickListener {
                    listener.onOtherButtonClicked(getItem(adapterPosition))
                    false
                }
                imageOther.setOnClickListener {
                    listener.onOtherButtonClicked(getItem(adapterPosition))
                }
            }
        }
        fun bind(music: Music){
            binding.music = music
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(listViewType == TYPE_ALL) {
            val binding =
                ItemMusicListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            AllViewHolder(binding)
        } else {
            val binding =
                ItemMusicBriefBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            BriefViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(listViewType == TYPE_ALL) (holder as AllViewHolder).bind(getItem(position))
        else if(listViewType == TYPE_BRIEF) (holder as BriefViewHolder).bind(getItem(position))
    }

    fun setListViewType(type: Int){
        listViewType = type
    }

    companion object{
        var listViewType = 0

        private val diffUtil = object : DiffUtil.ItemCallback<Music>(){
            override fun areItemsTheSame(oldItem: Music, newItem: Music): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Music, newItem: Music): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    }
}