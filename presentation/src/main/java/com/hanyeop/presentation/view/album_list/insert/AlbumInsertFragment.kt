package com.hanyeop.presentation.view.album_list.insert

import android.view.WindowManager
import androidx.fragment.app.activityViewModels
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseFragment
import com.hanyeop.presentation.databinding.FragmentAlbumInsertBinding
import com.hanyeop.presentation.view.album_list.AlbumViewModel

class AlbumInsertFragment : BaseFragment<FragmentAlbumInsertBinding>(R.layout.fragment_album_insert) {

    private val albumViewModel by activityViewModels<AlbumViewModel>()

    override fun init() {
        binding.apply {
            vm = albumViewModel
        }
    }

    override fun onResume() {
        super.onResume()
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }
}