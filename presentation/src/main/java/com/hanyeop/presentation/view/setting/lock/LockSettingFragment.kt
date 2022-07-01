package com.hanyeop.presentation.view.setting.lock

import androidx.navigation.fragment.findNavController
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseFragment
import com.hanyeop.presentation.databinding.FragmentLockSettingBinding

class LockSettingFragment : BaseFragment<FragmentLockSettingBinding>(R.layout.fragment_lock_setting) {

    override fun init() {

        initClickListener()
    }

    private fun initClickListener(){
        binding.apply {
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}