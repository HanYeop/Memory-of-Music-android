package com.hanyeop.presentation.view.music_list.list

import android.content.SharedPreferences
import android.os.Build
import android.view.*
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.hanyeop.domain.model.music.Music
import com.hanyeop.domain.utils.Result
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseFragmentMain
import com.hanyeop.presentation.databinding.FragmentMusicListBinding
import com.hanyeop.presentation.utils.*
import com.hanyeop.presentation.view.MainFragmentDirections
import com.hanyeop.presentation.view.MainViewModel
import com.hanyeop.presentation.view.music_list.MusicViewModel
import com.hanyeop.presentation.view.sort.SortDialog
import com.hanyeop.presentation.view.sort.SortListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MusicListFragment
    : BaseFragmentMain<FragmentMusicListBinding>(R.layout.fragment_music_list),
    MusicListAdapterListener, SortListener {

    private val musicViewModel by viewModels<MusicViewModel>()
    private val mainViewModel by activityViewModels<MainViewModel>()
    private val musicListAdapter = MusicListAdapter(this)
    private var searchView : SearchView? = null

    @Inject
    lateinit var sharedPref: SharedPreferences

    @RequiresApi(Build.VERSION_CODES.N)
    override fun init() {
        musicListAdapter.setHasStableIds(true)
        registerForContextMenu(binding.textToolbar)

        binding.apply {
            vm = musicViewModel
            toolbar.inflateMenu(R.menu.menu_music_list_option)
        }

        initSearchView()
        initAdapter()
        initClickListener()
        initViewModelCallback()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        MenuInflater(requireContext()).inflate(R.menu.menu_category, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_category_genre -> {
                showToast("장르 클릭됨")
            }
            R.id.menu_category_rating -> {
                showToast("평점 클릭됨")
            }
        }
        return super.onContextItemSelected(item)
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
                musicListAdapter.filter.filter(newText?.lowercase())
                return false
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun initClickListener(){
        binding.apply {
            toolbar.setOnMenuItemClickListener {
                if(it.itemId == R.id.menu_sort){
                    val dialog = SortDialog(requireContext(),this@MusicListFragment)
                    dialog.show()
                }
                false
            }
            fab.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_musicSearchFragment)
            }
            textToolbar.setOnClickListener {
                it.showContextMenu(it.x ,it.y + it.height/2)
            }
        }
    }

    private fun initAdapter(){
        musicListAdapter.setListViewType(sharedPref.getInt(LIST_TYPE,0))
        binding.apply {
            recyclerViewMusicList.adapter = musicListAdapter
        }
    }

    private fun initViewModelCallback(){
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

        lifecycleScope.launchWhenStarted {
            mainViewModel.listViewType.collectLatest {
                initAdapter()
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

    override fun onTimeDescClicked() {
        musicListAdapter.order(TIME_DESC)
    }

    override fun onTimeAscClicked() {
        musicListAdapter.order(TIME_ASC)
    }

    override fun onTitleAscClicked() {
        musicListAdapter.order(TITLE_ASC)
    }

    override fun onTitleDescClicked() {
        musicListAdapter.order(TITLE_DESC)
    }

    override fun onRatingDescClicked() {
        musicListAdapter.order(RATING_DESC)
    }

    override fun onRatingAscClicked() {
        musicListAdapter.order(RATING_ASC)
    }

    override fun onResume() {
        super.onResume()
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
    }
}