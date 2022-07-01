package com.hanyeop.presentation.view.lock

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseActivity
import com.hanyeop.presentation.databinding.ActivityLockBinding
import com.hanyeop.presentation.utils.FINGERPRINT_USE
import com.hanyeop.presentation.utils.PASSWORD
import com.hanyeop.presentation.utils.PASSWORD_USE
import com.hanyeop.presentation.view.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.Executor
import javax.inject.Inject

@AndroidEntryPoint
class LockActivity : BaseActivity<ActivityLockBinding>(R.layout.activity_lock) {

    @Inject
    lateinit var sharedPref: SharedPreferences

    private var focus = 1
    private val password = Array(5){ -1 }

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    override fun init() {
        if(sharedPref.getInt(PASSWORD_USE,0) == 0){
            startMainActivity()
        }

        initClickListener()
        initBiometric()

        if(sharedPref.getInt(FINGERPRINT_USE,0) == 1){
            biometricPrompt.authenticate(promptInfo)
        }
    }

    private fun initBiometric(){
        executor = ContextCompat.getMainExecutor(this@LockActivity)
        biometricPrompt = BiometricPrompt(this@LockActivity, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    startMainActivity()
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("지문 인증")
            .setNegativeButtonText("취소")
            .build()
    }

    private fun startMainActivity(){
        finish()
        val intent = Intent(this,MainActivity::class.java)
        intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
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
        var str = ""
        for(i in 1 until password.size){
            str += password[i].toString()
        }

        if(sharedPref.getString(PASSWORD,"default") == str) {
            startMainActivity()
        } else {
            val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibrator.vibrate(500)

            CoroutineScope(Dispatchers.Main).launch {
                binding.textLockBody.text = resources.getString(R.string.unlock_fail)
                resetColor()
                delay(500)
                binding.textLockBody.text = resources.getString(R.string.unlock_detail)
            }
        }
    }
}