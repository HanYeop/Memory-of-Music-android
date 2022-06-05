package com.hanyeop.presentation.view.music_list

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.hanyeop.domain.model.music.Music
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseFragmentMain
import com.hanyeop.presentation.databinding.FragmentMusicListBinding
import com.hanyeop.presentation.utils.TAG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MusicListFragment : BaseFragmentMain<FragmentMusicListBinding>(R.layout.fragment_music_list) {

    private val musicViewModel by viewModels<MusicViewModel>()
    private val musicListAdapter = MusicListAdapter()

    override fun init() {
        initAdapter()

        binding.button.setOnClickListener {
            musicViewModel.insertMusic(Music(title = "테스트2"))
        }
    }

    private fun initAdapter(){
        binding.recyclerViewMusicList.adapter = musicListAdapter

        lifecycleScope.launchWhenStarted {
            musicViewModel.musicList.collectLatest {
                musicListAdapter.submitList(musicViewModel.musicList.value)
                Log.d(TAG, "StateFlow : $it")
            }
        }
    }
}