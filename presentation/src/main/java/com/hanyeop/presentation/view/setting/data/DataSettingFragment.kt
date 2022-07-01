package com.hanyeop.presentation.view.setting.data

import androidx.navigation.fragment.findNavController
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseFragment
import com.hanyeop.presentation.databinding.FragmentDataSettingBinding

class DataSettingFragment : BaseFragment<FragmentDataSettingBinding>(R.layout.fragment_data_setting) {

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