package com.hanyeop.presentation.view.setting.lock.password

import android.content.Context
import android.content.SharedPreferences
import android.os.Vibrator
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseFragment
import com.hanyeop.presentation.databinding.FragmentPasswordSettingBinding
import com.hanyeop.presentation.utils.PASSWORD
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PasswordSettingFragment : BaseFragment<FragmentPasswordSettingBinding>(R.layout.fragment_password_setting) {

    @Inject
    lateinit var sharedPref: SharedPreferences

    private var focus = 1
    private val password = Array(5){ -1 }
    private var firstPassword = ""

    override fun init() {
        initClickListener()
    }

    private fun initClickListener(){
        binding.apply {
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            textOne.setOnClickListener {
                password[focus] = 1
                changeColor()
            }
            textTwo.setOnClickListener {
                password[focus] = 2
                changeColor()
            }
            textThree.setOnClickListener {
                password[focus] = 3
                changeColor()
            }
            textFour.setOnClickListener {
                password[focus] = 4
                changeColor()
            }
            textFive.setOnClickListener {
                password[focus] = 5
                changeColor()
            }
            textSix.setOnClickListener {
                password[focus] = 6
                changeColor()
            }
            textSeven.setOnClickListener {
                password[focus] = 7
                changeColor()
            }
            textEight.setOnClickListener {
                password[focus] = 8
                changeColor()
            }
            textNine.setOnClickListener {
                password[focus] = 9
                changeColor()
            }
            textZero.setOnClickListener {
                password[focus] = 0
                changeColor()
            }
            imageBackspace.setOnClickListener {

                deleteColor()
            }
        }
    }

    private fun changeColor(){
        when(focus){
            1 -> {
                binding.passwordOne.setTextColor(ContextCompat.getColor(requireContext(), R.color.subColor))
            }
            2 -> {
                binding.passwordTwo.setTextColor(ContextCompat.getColor(requireContext(), R.color.subColor))
            }
            3 -> {
                binding.passwordThree.setTextColor(ContextCompat.getColor(requireContext(), R.color.subColor))
            }
            4 -> {
                binding.passwordFour.setTextColor(ContextCompat.getColor(requireContext(), R.color.subColor))
                if(firstPassword == ""){
                    setFirstPassword()
                }else{
                    changePassword()
                }
                focus = 0
            }
        }
        focus++
    }

    private fun deleteColor(){
        if(focus > 1) {
            focus--
            when (focus) {
                1 -> {
                    binding.passwordOne.setTextColor(ContextCompat.getColor(requireContext(), R.color.light_gray))
                }
                2 -> {
                    binding.passwordTwo.setTextColor(ContextCompat.getColor(requireContext(), R.color.light_gray))
                }
                3 -> {
                    binding.passwordThree.setTextColor(ContextCompat.getColor(requireContext(), R.color.light_gray))
                }
                4 -> {
                    binding.passwordFour.setTextColor(ContextCompat.getColor(requireContext(), R.color.light_gray))
                }
            }
            password[focus] = -1
        }
    }

    private fun resetColor(){
        focus = 1
        for(i in password.indices){
            password[i] = -1
        }
        binding.passwordOne.setTextColor(ContextCompat.getColor(requireContext(), R.color.light_gray))
        binding.passwordTwo.setTextColor(ContextCompat.getColor(requireContext(), R.color.light_gray))
        binding.passwordThree.setTextColor(ContextCompat.getColor(requireContext(), R.color.light_gray))
        binding.passwordFour.setTextColor(ContextCompat.getColor(requireContext(), R.color.light_gray))
    }

    private fun setFirstPassword(){
        for(i in 1 until password.size){
            firstPassword += password[i].toString()
        }
        binding.textLockBody.text = resources.getString(R.string.password_setting_body_second)
        resetColor()
    }

    private fun changePassword(){
        var str = ""
        for(i in 1 until password.size){
            str += password[i].toString()
        }

        if(firstPassword == str){
            sharedPref.edit().putString(PASSWORD, str).apply()
            showToast(resources.getString(R.string.password_setting_success))
            findNavController().navigateUp()
        }else{
            val vibrator = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibrator.vibrate(500)
            binding.textLockBody.text = resources.getString(R.string.password_setting_fail)
            resetColor()
        }
    }
}