package com.hanyeop.presentation.view

import androidx.navigation.fragment.NavHostFragment
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseActivity
import com.hanyeop.presentation.databinding.ActivityMainBinding
import github.com.st235.lib_expandablebottombar.navigation.ExpandableBottomBarNavigationUI

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun init() {
        // 바텀네비게이션뷰 <-> 네비게이션 연결
        initNavigation()

        initClickListener()
    }

    private fun initNavigation(){
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navHostFragment.navController
        ExpandableBottomBarNavigationUI.setupWithNavController(binding.expandableBottomBar, navController)
    }

    private fun initClickListener(){
        binding.apply {

        }
    }
}