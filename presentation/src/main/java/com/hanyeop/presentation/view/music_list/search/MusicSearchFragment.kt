package com.hanyeop.presentation.view.music_list.search

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hanyeop.domain.model.music.DomainMusicResponse
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseFragmentMain
import com.hanyeop.presentation.databinding.FragmentMusicSearchBinding
import com.hanyeop.presentation.view.music_list.MusicViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MusicSearchFragment
    : BaseFragmentMain<FragmentMusicSearchBinding>(R.layout.fragment_music_search),
     MusicSearchAdapterListener  {

    private val musicViewModel by activityViewModels<MusicViewModel>()
    private val musicSearchAdapter = MusicSearchAdapter(this)

    override fun init() {
        binding.apply {
            vm = musicViewModel
            recyclerViewMusicSearchList.adapter = musicSearchAdapter
        }
        initSearchView()
        initClickListener()
    }

    private fun initSearchView(){
        binding.searchViewMusic.apply {
            val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            CoroutineScope(Dispatchers.Main).launch {
                inputMethodManager.showSoftInput(this@apply,0)
            }
            setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String): Boolean {
                    musicViewModel.getRemoteMusics(query)
                    clearFocus()
                    return true
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        }
    }

    private fun initClickListener(){
        binding.apply {
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun onItemClicked(musicInfo: DomainMusicResponse) {
        musicViewModel.setMusicInfo(musicInfo)
        findNavController().navigate(R.id.action_musicSearchFragment_to_musicInsertFragment)
    }
}