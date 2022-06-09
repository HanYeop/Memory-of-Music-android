package com.hanyeop.presentation.view.music_list.search

import android.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hanyeop.domain.model.music.DomainMusicResponse
import com.hanyeop.domain.model.music.Music
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseFragment
import com.hanyeop.presentation.databinding.FragmentMusicSearchBinding
import com.hanyeop.presentation.view.music_list.MusicViewModel
import com.hanyeop.presentation.view.music_list.insert.MusicInsertDialogListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MusicSearchFragment
    : BaseFragment<FragmentMusicSearchBinding>(R.layout.fragment_music_search),
     MusicSearchAdapterListener, MusicInsertDialogListener  {

    private val musicViewModel by activityViewModels<MusicViewModel>()
    private val musicSearchAdapter = MusicSearchAdapter(this)

    override fun init() {
        binding.apply {
            vm = musicViewModel
            recyclerViewMusicSearchList.adapter = musicSearchAdapter
        }

//        musicViewModel.getRemoteMusics("물고기")
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
//        MusicInsertDialog(requireContext(),this, musicInfo).show()
//        val action = MusicSearchFragmentDirections.actionMusicSearchFragmentToMusicInsertFragment(musicInfo)
//        findNavController().navigate(action)
        musicViewModel.setMusicInfo(musicInfo)
        findNavController().navigate(R.id.action_musicSearchFragment_to_musicInsertFragment)
    }

    // 다이얼로그에서 OK 버튼 클릭 시
    override fun onOkButtonClicked(music: Music) {
//        Toast.makeText(requireContext(), "음악이 등록되었습니다.", Toast.LENGTH_SHORT).show()
//        musicViewModel.insertMusic(music)
//        findNavController().popBackStack()
    }
}