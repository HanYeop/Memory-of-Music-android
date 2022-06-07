package com.hanyeop.presentation.view.music_list.search

import android.widget.SearchView
import androidx.fragment.app.viewModels
import com.hanyeop.domain.model.music.DomainMusicResponse
import com.hanyeop.domain.model.music.Music
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseFragment
import com.hanyeop.presentation.databinding.FragmentMusicSearchBinding
import com.hanyeop.presentation.view.music_list.MusicViewModel
import com.hanyeop.presentation.view.music_list.insert.MusicInsertDialog
import com.hanyeop.presentation.view.music_list.insert.MusicInsertDialogListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MusicSearchFragment
    : BaseFragment<FragmentMusicSearchBinding>(R.layout.fragment_music_search),
     MusicSearchAdapterListener, MusicInsertDialogListener  {

    private val musicViewModel by viewModels<MusicViewModel>()
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

    // 리사이클러뷰 아이템 클릭 시
    override fun onItemClicked(musicInfo: DomainMusicResponse) {
        MusicInsertDialog(requireContext(),this, musicInfo).show()
    }

    // 다이얼로그에서 OK 버튼 클릭 시
    override fun onOkButtonClicked(music: Music) {
        musicViewModel.insertMusic(music)
    }
}