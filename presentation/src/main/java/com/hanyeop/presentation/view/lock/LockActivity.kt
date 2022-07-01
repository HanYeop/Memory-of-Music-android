package com.hanyeop.presentation.view.lock

import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseActivity
import com.hanyeop.presentation.databinding.ActivityLockBinding

class LockActivity : BaseActivity<ActivityLockBinding>(R.layout.activity_lock) {
    override fun init() {
        initClickListener()
    }

    private fun initClickListener(){
        binding.apply {
            textOne.setOnClickListener {

            }
            textTwo.setOnClickListener {

            }
        }
    }
}