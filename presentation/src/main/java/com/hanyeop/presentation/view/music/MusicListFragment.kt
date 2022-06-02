package com.hanyeop.presentation.view.music

import com.hanyeop.domain.model.music.Music
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseFragment
import com.hanyeop.presentation.databinding.FragmentMusicListBinding

class MusicListFragment : BaseFragment<FragmentMusicListBinding>(R.layout.fragment_music_list) {

    private val musicAdapter = MusicAdapter()

    override fun init() {
        initAdapter()
    }

    private fun initAdapter(){
        binding.recyclerViewMusicList.adapter = musicAdapter
        val list = mutableListOf(Music("d","d","d","d",0f,"d","d"))
        for(i in 0 until 10){
            list.add(Music("d","d","d","d",0f,"d","d"))
        }
        musicAdapter.submitList(list)
    }
}