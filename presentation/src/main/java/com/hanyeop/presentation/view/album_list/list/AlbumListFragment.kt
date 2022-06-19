package com.hanyeop.presentation.view.album_list.list

import android.content.SharedPreferences
import android.view.WindowManager
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseFragmentMain
import com.hanyeop.presentation.databinding.FragmentAlbumListBinding
import com.hanyeop.presentation.view.MainViewModel
import com.hanyeop.presentation.view.album_list.AlbumViewModel
import com.hanyeop.presentation.view.sort.SortDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AlbumListFragment : BaseFragmentMain<FragmentAlbumListBinding>(R.layout.fragment_album_list) {

    private val albumViewModel by viewModels<AlbumViewModel>()
    private val mainViewModel by activityViewModels<MainViewModel>()

    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun init() {
        binding.apply {
            vm = albumViewModel
            toolbar.inflateMenu(R.menu.menu_music_list_option)
        }
        initClickListener()
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
        }
    }

    override fun onResume() {
        super.onResume()
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
    }
}