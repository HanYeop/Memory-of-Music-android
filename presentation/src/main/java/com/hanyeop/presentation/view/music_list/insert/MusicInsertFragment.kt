package com.hanyeop.presentation.view.music_list.insert

import androidx.navigation.fragment.navArgs
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseFragment
import com.hanyeop.presentation.databinding.FragmentMusicInsertBinding

class MusicInsertFragment : BaseFragment<FragmentMusicInsertBinding>(R.layout.fragment_music_insert) {

    private val args by navArgs<MusicInsertFragmentArgs>()

    override fun init() {
        binding.musicInfo = args.musicinfo
    }
}