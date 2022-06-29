package com.hanyeop.presentation.view.setting.inquiry

import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseFragment
import com.hanyeop.presentation.databinding.FragmentInquiryBinding
import com.hanyeop.presentation.utils.repeatOnStarted
import com.hanyeop.presentation.view.setting.SettingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class InquiryFragment : BaseFragment<FragmentInquiryBinding>(R.layout.fragment_inquiry) {

    private val settingViewModel by viewModels<SettingViewModel>()

    override fun init() {
        binding.apply {
            vm = settingViewModel
        }

        initClickListener()
        initViewModelCallback()
    }

    private fun initClickListener(){
        binding.apply {
            btnCancel.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun initViewModelCallback(){
        repeatOnStarted {
            settingViewModel.successMsg.collectLatest {
                showToast(resources.getString(it))
                findNavController().navigateUp()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }
}