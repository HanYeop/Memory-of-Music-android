package com.hanyeop.presentation.view.music_list

import com.hanyeop.domain.model.music.Music
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseFragmentMain
import com.hanyeop.presentation.databinding.FragmentMusicListBinding

class MusicListFragment : BaseFragmentMain<FragmentMusicListBinding>(R.layout.fragment_music_list) {

    private val musicListAdapter = MusicListAdapter()

    override fun init() {
        initAdapter()
    }

    private fun initAdapter(){
        binding.recyclerViewMusicList.adapter = musicListAdapter
        val list = mutableListOf(Music("d","d","d","d",0f,"d","d"))
        for(i in 0 until 10){
            list.add(Music("d","d","d","d",0f,"d","d"))
        }
        musicListAdapter.submitList(list)
    }
}