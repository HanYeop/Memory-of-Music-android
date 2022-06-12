package com.hanyeop.presentation.view.music_list.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hanyeop.domain.model.music.Music
import com.hanyeop.presentation.databinding.ItemMusicListBinding
import com.hanyeop.presentation.utils.*

class MusicListAdapter(private val listener: MusicListAdapterListener)
    : ListAdapter<Music, MusicListAdapter.ViewHolder>(diffUtil),Filterable {

    private var originalList = arrayListOf<Music>()
    private var filterList = arrayListOf<Music>()

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                return FilterResults().apply {
                    values = if (constraint.isNullOrEmpty()){
                        originalList
                    }else{
                        originalList.filter { it.title.contains(constraint.toString()) }
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
                originalList.sortBy { it.title }
                filterList.sortBy { it.title }
            }
            TITLE_DESC ->{
                originalList.sortBy { it.title }
                originalList.reverse()
                filterList.sortBy { it.title }
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

    inner class ViewHolder(private val binding: ItemMusicListBinding):RecyclerView.ViewHolder(binding.root){
        init {
            binding.apply {
                root.setOnClickListener {
                    listener.onItemClicked(getItem(adapterPosition))
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMusicListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

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