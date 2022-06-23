package com.hanyeop.presentation.view.album_list.list

import android.content.SharedPreferences
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.hanyeop.domain.model.album.Album
import com.hanyeop.domain.utils.Result
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseFragmentMain
import com.hanyeop.presentation.databinding.FragmentAlbumListBinding
import com.hanyeop.presentation.utils.LIST_TYPE
import com.hanyeop.presentation.view.MainFragmentDirections
import com.hanyeop.presentation.view.MainViewModel
import com.hanyeop.presentation.view.album_list.AlbumViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class AlbumListFragment
    : BaseFragmentMain<FragmentAlbumListBinding>(R.layout.fragment_album_list), AlbumListAdapterListener {

    private val albumViewModel by viewModels<AlbumViewModel>()
    private val mainViewModel by activityViewModels<MainViewModel>()
    private val albumListAdapter = AlbumListAdapter(this)
    private var searchView : SearchView? = null
    private lateinit var job : Job

    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun init() {
        binding.apply {
            vm = albumViewModel
            toolbar.inflateMenu(R.menu.menu_music_list_option)
        }
        initSearchView()
        initAdapter()
        initClickListener()
        initViewModelCallback()
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
                albumListAdapter.filter.filter(newText?.lowercase())
                return false
            }
        })
    }

    private fun initClickListener(){
        binding.apply {
//            toolbar.setOnMenuItemClickListener {
//                if(it.itemId == R.id.menu_sort){
//                    val dialog = SortDialog(requireContext(),this@MusicListFragment)
//                    dialog.show()
//                }
//                false
//            }
            fab.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_albumSearchFragment)
            }
//            textToolbar.setOnClickListener {
//                CategoryDialog(requireContext(),this@MusicListFragment).show()
//            }
//            imageReset.setOnClickListener {
//                job.cancel()
//                musicViewModel.resetMusicList()
//                collectMusicList()
//                showToast(resources.getString(R.string.filter_reset))
//            }
        }
    }

    private fun initAdapter(){
        albumListAdapter.setListViewType(sharedPref.getInt(LIST_TYPE,0))
        binding.apply {
            recyclerViewAlbumList.adapter = albumListAdapter
        }
    }

    private fun initViewModelCallback(){
        collectMusicList()

        lifecycleScope.launchWhenStarted {
            mainViewModel.listViewTypeAlbum.collectLatest {
                initAdapter()
            }
        }
    }

    private fun collectMusicList(){
        job = lifecycleScope.launchWhenStarted {
            albumViewModel.albumList.collect {
                Log.d("test5", "collectMusicList: test")
                if(it is Result.Success){
//                    searchView?.setQuery("",false)
                    albumListAdapter.setItem(it.data)
//                    filterSort(musicViewModel.filterSort.value)
                }else{
                    albumListAdapter.setItem(mutableListOf())
                }
            }
        }
    }

//    private fun filterSort(type: Int){
//        musicListAdapter.order(type)
//    }

    override fun onItemClicked(album: Album) {
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToAlbumDetailFragment(album))
    }

    override fun onOtherButtonClicked(album: Album) {
    }

//    override fun onSortClicked(type: Int) {
//        musicListAdapter.order(type)
//        musicViewModel.setFilterSort(type)
//    }
//
//    override fun onCategorySelected(start: Float, end: Float, genre: String) {
//        job.cancel()
//        musicViewModel.changeMusicList(start, end, genre)
//        collectMusicList()
//    }

    override fun onResume() {
        super.onResume()
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
    }
}