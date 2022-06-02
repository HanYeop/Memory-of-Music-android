package com.hanyeop.presentation.view.music

import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseActivity
import com.hanyeop.presentation.databinding.ActivityMusicSearchBinding

class MusicSearchActivity : BaseActivity<ActivityMusicSearchBinding>(R.layout.activity_music_search) {

    override fun init() {
        initClickListener()
    }

    private fun initClickListener(){
        binding.apply {
            btn.setOnClickListener {

            }
        }
    }
}