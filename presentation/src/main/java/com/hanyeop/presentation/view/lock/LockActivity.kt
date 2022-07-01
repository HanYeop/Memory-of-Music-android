package com.hanyeop.presentation.view.lock

import android.util.Log
import androidx.core.content.ContextCompat
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseActivity
import com.hanyeop.presentation.databinding.ActivityLockBinding
import kotlinx.coroutines.*

class LockActivity : BaseActivity<ActivityLockBinding>(R.layout.activity_lock) {

    private var focus = 1
    private val password = Array(5){ -1 }

    override fun init() {
        initClickListener()
    }

    private fun initClickListener(){
        binding.apply {
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
                binding.passwordOne.setTextColor(ContextCompat.getColor(this, R.color.subColor))
            }
            2 -> {
                binding.passwordTwo.setTextColor(ContextCompat.getColor(this, R.color.subColor))
            }
            3 -> {
                binding.passwordThree.setTextColor(ContextCompat.getColor(this, R.color.subColor))
            }
            4 -> {
                binding.passwordFour.setTextColor(ContextCompat.getColor(this, R.color.subColor))
                checkPassword()
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
                    binding.passwordOne.setTextColor(ContextCompat.getColor(this, R.color.light_gray))
                }
                2 -> {
                    binding.passwordTwo.setTextColor(ContextCompat.getColor(this, R.color.light_gray))
                }
                3 -> {
                    binding.passwordThree.setTextColor(ContextCompat.getColor(this, R.color.light_gray))
                }
                4 -> {
                    binding.passwordFour.setTextColor(ContextCompat.getColor(this, R.color.light_gray))
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
        binding.passwordOne.setTextColor(ContextCompat.getColor(this, R.color.light_gray))
        binding.passwordTwo.setTextColor(ContextCompat.getColor(this, R.color.light_gray))
        binding.passwordThree.setTextColor(ContextCompat.getColor(this, R.color.light_gray))
        binding.passwordFour.setTextColor(ContextCompat.getColor(this, R.color.light_gray))
    }

    private fun checkPassword(){
        CoroutineScope(Dispatchers.Main).launch {
            binding.textLockBody.text = resources.getString(R.string.unlock_fail)
            resetColor()
            delay(500)
            binding.textLockBody.text = resources.getString(R.string.unlock_detail)
        }
    }
}