package com.hanyeop.presentation.view.album_list

import androidx.fragment.app.viewModels
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseFragmentMain
import com.hanyeop.presentation.databinding.FragmentAlbumListBinding
import com.hanyeop.presentation.view.music_list.MusicViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumListFragment : BaseFragmentMain<FragmentAlbumListBinding>(R.layout.fragment_album_list) {

    private val musicViewModel by viewModels<MusicViewModel>()

    override fun init() {
        binding.apply {
            vm = musicViewModel
            toolbar.inflateMenu(R.menu.menu_music_list_option)
        }
    }
}