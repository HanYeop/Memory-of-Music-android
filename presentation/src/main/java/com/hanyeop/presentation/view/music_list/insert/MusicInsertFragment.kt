package com.hanyeop.presentation.view.music_list.insert

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseFragment
import com.hanyeop.presentation.databinding.FragmentMusicInsertBinding
import com.hanyeop.presentation.view.music_list.MusicViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MusicInsertFragment
    : BaseFragment<FragmentMusicInsertBinding>(R.layout.fragment_music_insert), MusicRatingListener {

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
                MusicRatingDialog(requireContext(),this@MusicInsertFragment).show()
            }
            btnCancel.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun onOkClick(rating: Float) {
        musicViewModel.insertMusic(rating)
    }
}