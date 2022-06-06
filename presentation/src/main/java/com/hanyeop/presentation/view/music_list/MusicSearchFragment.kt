package com.hanyeop.presentation.view.music_list

import androidx.fragment.app.viewModels
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseFragment
import com.hanyeop.presentation.databinding.FragmentMusicSearchBinding
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
        musicViewModel.getRemoteMusics("물고기")
    }
}