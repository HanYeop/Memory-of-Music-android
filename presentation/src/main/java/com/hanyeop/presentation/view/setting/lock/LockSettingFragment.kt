package com.hanyeop.presentation.view.setting.lock

import android.content.SharedPreferences
import androidx.navigation.fragment.findNavController
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseFragment
import com.hanyeop.presentation.databinding.FragmentLockSettingBinding
import com.hanyeop.presentation.utils.FINGERPRINT_USE
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
            textPasswordChange.setOnClickListener {
                findNavController().navigate(R.id.action_lockSettingFragment_to_passwordSettingFragment)
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
                    sharedPref.edit().putInt(FINGERPRINT_USE, 0).apply()
                    switchFingerprint.isChecked = false
                }
            }

            switchFingerprint.isChecked = sharedPref.getInt(FINGERPRINT_USE,0) != 0
            switchFingerprint.setOnCheckedChangeListener { _, isChecked ->
                if(isChecked){
                    if(sharedPref.getInt(PASSWORD_USE,0) == 0){
                        showToast("지문 인증을 사용하기 위해서는 비밀번호 인증을 사용해야 합니다.")
                        switchFingerprint.isChecked = false
                    } else{
                        sharedPref.edit().putInt(FINGERPRINT_USE, 1).apply()
                    }
                }else{
                    sharedPref.edit().putInt(FINGERPRINT_USE, 0).apply()
                }
            }
        }
    }
}