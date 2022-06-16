package com.hanyeop.presentation.view.music_list.modify

import android.view.WindowManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseFragment
import com.hanyeop.presentation.databinding.FragmentMusicModifyBinding
import com.hanyeop.presentation.view.music_list.MusicViewModel
import com.hanyeop.presentation.view.rating.RatingDialog
import com.hanyeop.presentation.view.rating.RatingListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MusicModifyFragment
    : BaseFragment<FragmentMusicModifyBinding>(R.layout.fragment_music_modify),
    RatingListener {

    private val musicViewModel by activityViewModels<MusicViewModel>()

    override fun init() {
        binding.apply {
            vm = musicViewModel
        }

        initViewModelCallback()
        initClickListener()
    }

    private fun initViewModelCallback(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                musicViewModel.inputErrorEvent.collectLatest {
                    showToast(resources.getString(it))
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                musicViewModel.inputSuccessEvent.collectLatest {
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
                RatingDialog(requireContext(),this@MusicModifyFragment).show()
            }
            btnCancel.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun onOkClick(rating: Float) {
        musicViewModel.updateMusic(rating)
    }

    override fun onResume() {
        super.onResume()
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }
}