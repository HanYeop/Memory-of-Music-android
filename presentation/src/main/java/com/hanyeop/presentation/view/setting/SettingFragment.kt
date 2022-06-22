package com.hanyeop.presentation.view.setting

import android.content.SharedPreferences
import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseFragmentMain
import com.hanyeop.presentation.databinding.FragmentSettingBinding
import com.hanyeop.presentation.utils.LIST_TYPE
import com.hanyeop.presentation.view.MainViewModel
import com.hanyeop.presentation.view.music_list.MusicViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class SettingFragment : BaseFragmentMain<FragmentSettingBinding>(R.layout.fragment_setting) {

    private val mainViewModel by activityViewModels<MainViewModel>()
    private val musicViewModel by viewModels<MusicViewModel>()

    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun init() {
        binding.apply {
            mainVm = mainViewModel
            musicVm = musicViewModel
        }
        initSwitch()
        initViewModelCallback()
    }

    private fun initSwitch(){
        binding.apply {
            switchLayoutType.isChecked = sharedPref.getInt(LIST_TYPE,0) != 0
            switchLayoutType.setOnCheckedChangeListener { _, isChecked ->
                if(isChecked){
                    sharedPref.edit().putInt(LIST_TYPE, 1).apply()
                }else{
                    sharedPref.edit().putInt(LIST_TYPE, 0).apply()
                }
                mainViewModel.setListViewType()
                mainViewModel.setListViewTypeAlbum()
            }
        }
    }

    private fun initViewModelCallback(){

    }
}