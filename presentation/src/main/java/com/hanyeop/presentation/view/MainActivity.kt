package com.hanyeop.presentation.view

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseActivity
import com.hanyeop.presentation.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun init() {
        initNavigation()
    }

    // 바텀네비게이션뷰 <-> 네비게이션 연결
    private fun initNavigation(){
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
    }
}