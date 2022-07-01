package com.hanyeop.presentation.view.setting.lock

import android.content.SharedPreferences
import androidx.navigation.fragment.findNavController
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseFragment
import com.hanyeop.presentation.databinding.FragmentLockSettingBinding
import com.hanyeop.presentation.utils.PASSWORD
import com.hanyeop.presentation.utils.PASSWORD_USE
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LockSettingFragment : BaseFragment<FragmentLockSettingBinding>(R.layout.fragment_lock_setting) {

    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun init() {
        initSwitch()
        initClickListener()
    }

    private fun initClickListener(){
        binding.apply {
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun initSwitch(){
        binding.apply {
            switchPassword.isChecked = sharedPref.getInt(PASSWORD_USE,0) != 0
            switchPassword.setOnCheckedChangeListener { _, isChecked ->
                if(isChecked){
                    if(sharedPref.getString(PASSWORD,"default") == "default"){
                        findNavController().navigate(R.id.action_lockSettingFragment_to_passwordSettingFragment)
                        switchPassword.isChecked = false
                    } else{
                        sharedPref.edit().putInt(PASSWORD_USE, 1).apply()
                    }
                }else{
                    sharedPref.edit().putInt(PASSWORD_USE, 0).apply()
                }
            }
        }
    }
}