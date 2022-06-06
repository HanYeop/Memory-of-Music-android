package com.hanyeop.presentation.view.music_list.search

import android.widget.SearchView
import androidx.fragment.app.viewModels
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseFragment
import com.hanyeop.presentation.databinding.FragmentMusicSearchBinding
import com.hanyeop.presentation.view.music_list.MusicViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MusicSearchFragment : BaseFragment<FragmentMusicSearchBinding>(R.layout.fragment_music_search) {

    private val musicViewModel by viewModels<MusicViewModel>()
    private val musicSearchAdapter = MusicSearchAdapter()

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
}