package com.hanyeop.presentation.view.music_list.detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseFragment
import com.hanyeop.presentation.databinding.FragmentMusicDetailBinding
import com.hanyeop.presentation.view.music_list.MusicViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MusicDetailFragment : BaseFragment<FragmentMusicDetailBinding>(R.layout.fragment_music_detail) {

    private val args by navArgs<MusicDetailFragmentArgs>()
    private val musicViewModel by viewModels<MusicViewModel>()

    override fun init() {
        binding.music = args.music

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