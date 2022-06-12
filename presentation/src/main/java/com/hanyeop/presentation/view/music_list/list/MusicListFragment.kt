package com.hanyeop.presentation.view.music_list.list

import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.hanyeop.domain.model.music.Music
import com.hanyeop.domain.utils.Result
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseFragmentMain
import com.hanyeop.presentation.databinding.FragmentMusicListBinding
import com.hanyeop.presentation.view.MainFragmentDirections
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
            toolbar.inflateMenu(R.menu.menu_music_list_option)
        }
        initClickListener()
        initAdapter()
    }

    private fun initClickListener(){
        binding.searchViewMusic.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String): Boolean {
                    clearFocus()
                    return true
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    musicListAdapter.filter.filter(newText)
                    return false
                }
            })
        }
    }

    private fun initAdapter(){
        lifecycleScope.launchWhenStarted {
            musicViewModel.musicList.collect {
                if(it is Result.Success){
                    musicListAdapter.setItem(it.data)
                }
            }
        }
    }

    override fun onItemClicked(music: Music) {
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToMusicDetailFragment(music))
    }

    override fun onOtherButtonClicked(music: Music) {
        val dialog = MusicBottomSheet(music)
        dialog.show(childFragmentManager,dialog.tag)
    }
}