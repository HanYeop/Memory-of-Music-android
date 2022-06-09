package com.hanyeop.presentation.view.music_list.search

import android.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hanyeop.domain.model.music.DomainMusicResponse
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseFragment
import com.hanyeop.presentation.databinding.FragmentMusicSearchBinding
import com.hanyeop.presentation.view.music_list.MusicViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MusicSearchFragment
    : BaseFragment<FragmentMusicSearchBinding>(R.layout.fragment_music_search),
     MusicSearchAdapterListener  {

    private val musicViewModel by activityViewModels<MusicViewModel>()
    private val musicSearchAdapter = MusicSearchAdapter(this)

    override fun init() {
        binding.apply {
            vm = musicViewModel
            recyclerViewMusicSearchList.adapter = musicSearchAdapter
        }
        initSearchView()
    }

    private fun initSearchView(){
        binding.searchViewMusic.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                musicViewModel.getRemoteMusics(query)
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override fun onItemClicked(musicInfo: DomainMusicResponse) {
        musicViewModel.setMusicInfo(musicInfo)
        findNavController().navigate(R.id.action_musicSearchFragment_to_musicInsertFragment)
    }
}