package com.hanyeop.presentation.view.music_list.list

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hanyeop.domain.model.music.Music
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseFragmentMain
import com.hanyeop.presentation.databinding.FragmentMusicListBinding
import com.hanyeop.presentation.view.music_list.MusicViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MusicListFragment
    : BaseFragmentMain<FragmentMusicListBinding>(R.layout.fragment_music_list), MusicListAdapterListener {

    private val musicViewModel by viewModels<MusicViewModel>()
    private val musicListAdapter = MusicListAdapter(this)

    override fun init() {
        binding.apply {
            vm = musicViewModel
            recyclerViewMusicList.adapter = musicListAdapter
        }
    }

    override fun onItemClicked(music: Music) {
        findNavController().navigate(R.id.action_mainFragment_to_musicDetailFragment)
    }
}