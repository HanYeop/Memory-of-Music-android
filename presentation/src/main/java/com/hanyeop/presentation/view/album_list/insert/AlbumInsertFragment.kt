package com.hanyeop.presentation.view.album_list.insert

import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseFragment
import com.hanyeop.presentation.databinding.FragmentAlbumInsertBinding
import com.hanyeop.presentation.view.album_list.AlbumViewModel
import com.hanyeop.presentation.view.rating.RatingDialog
import com.hanyeop.presentation.view.rating.RatingListener
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AlbumInsertFragment
    : BaseFragment<FragmentAlbumInsertBinding>(R.layout.fragment_album_insert), RatingListener {

    private val albumViewModel by activityViewModels<AlbumViewModel>()

    override fun init() {
        binding.apply {
            vm = albumViewModel
        }

        initViewModelCallback()
        initClickListener()
        initSpinner()
    }

    private fun initViewModelCallback(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                albumViewModel.inputErrorEvent.collectLatest {
                    showToast(resources.getString(it))
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                albumViewModel.inputSuccessEvent.collectLatest {
                    showToast(resources.getString(it))
                    findNavController().navigateUp()
                    findNavController().navigateUp()
                }
            }
        }
    }

    private fun initClickListener(){
        binding.apply {
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            btnOk.setOnClickListener {
                RatingDialog(requireContext(),this@AlbumInsertFragment).show()
            }
            btnCancel.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun initSpinner(){
        val spinnerEntries = resources.getStringArray(R.array.genre)

        binding.spinnerGenre.apply {
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    albumViewModel.setGenre(spinnerEntries[position])
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        }
    }

    override fun onOkClick(rating: Float) {
        albumViewModel.insertAlbum(rating)
    }

    override fun onResume() {
        super.onResume()
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }
}