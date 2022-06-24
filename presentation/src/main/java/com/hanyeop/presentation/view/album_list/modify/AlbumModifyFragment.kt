package com.hanyeop.presentation.view.album_list.modify

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
import com.hanyeop.presentation.databinding.FragmentAlbumModifyBinding
import com.hanyeop.presentation.utils.repeatOnStarted
import com.hanyeop.presentation.view.album_list.AlbumViewModel
import com.hanyeop.presentation.view.rating.RatingDialog
import com.hanyeop.presentation.view.rating.RatingListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AlbumModifyFragment : BaseFragment<FragmentAlbumModifyBinding>(R.layout.fragment_album_modify),
    RatingListener {

    private val albumViewModel by activityViewModels<AlbumViewModel>()

    override fun init() {
        binding.apply {
            vm = albumViewModel
        }

        initViewModelCallback()
        initClickListener()
        initSpinner()
    }

    private fun initViewModelCallback() {
        repeatOnStarted {
            albumViewModel.inputErrorMsg.collectLatest {
                showToast(resources.getString(it))
            }
        }

        repeatOnStarted {
            albumViewModel.insertSuccessMsg.collectLatest {
                showToast(resources.getString(it))
                findNavController().navigateUp()
                findNavController().navigateUp()
            }
        }

        repeatOnStarted {
            albumViewModel.inputSuccessEvent.collectLatest {
                RatingDialog(requireContext(), this@AlbumModifyFragment).show()
            }
        }
    }

    private fun initClickListener() {
        binding.apply {
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            btnCancel.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun initSpinner() {
        val spinnerEntries = resources.getStringArray(R.array.genre)

        binding.spinnerGenre.apply {
            for (i in spinnerEntries.indices) {
                if (albumViewModel.genre.value == spinnerEntries[i]) {
                    setSelection(i)
                    break
                }
            }
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
        albumViewModel.updateAlbum(rating)
    }

    override fun onResume() {
        super.onResume()
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }
}