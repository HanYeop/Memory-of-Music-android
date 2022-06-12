package com.hanyeop.presentation.view.music_list.list

import androidx.appcompat.widget.SearchView
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
import com.hanyeop.presentation.view.sort.SortDialog
import com.hanyeop.presentation.view.sort.SortListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MusicListFragment
    : BaseFragmentMain<FragmentMusicListBinding>(R.layout.fragment_music_list),
    MusicListAdapterListener, SortListener {

    private val musicViewModel by viewModels<MusicViewModel>()
    private val musicListAdapter = MusicListAdapter(this)
    private var searchView : SearchView? = null

    override fun init() {
        binding.apply {
            vm = musicViewModel
            recyclerViewMusicList.adapter = musicListAdapter
            toolbar.inflateMenu(R.menu.menu_music_list_option)
        }
        initSearchView()
        initAdapter()
        initClickListener()
    }

    private fun initSearchView(){
        val search = binding.toolbar.menu.findItem(R.id.menu_search)
        searchView = search.actionView as SearchView

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                searchView?.clearFocus()
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                musicListAdapter.filter.filter(newText)
                return false
            }
        })
    }

    private fun initClickListener(){
        binding.apply {
            toolbar.setOnMenuItemClickListener {
                if(it.itemId == R.id.menu_sort){
                    val dialog = SortDialog(requireContext(),this@MusicListFragment)
                    dialog.show()
                }
                false
            }
        }
    }

    private fun initAdapter(){
        lifecycleScope.launchWhenStarted {
            musicViewModel.musicList.collectLatest {
                if(it is Result.Success){
                    searchView?.setQuery("",false)
                    musicListAdapter.setItem(it.data)
                }else{
                    musicListAdapter.setItem(mutableListOf())
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