package com.hanyeop.presentation.view.music_list.detail

import androidx.navigation.fragment.findNavController
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseFragment
import com.hanyeop.presentation.databinding.FragmentMusicDetailBinding

class MusicDetailFragment : BaseFragment<FragmentMusicDetailBinding>(R.layout.fragment_music_detail) {
    override fun init() {
        initClickListener()
    }

    private fun initClickListener(){
        binding.apply {
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}