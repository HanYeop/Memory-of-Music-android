package com.hanyeop.presentation.view

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseFragment
import com.hanyeop.presentation.databinding.FragmentMusicListBinding

class MusicListFragment : BaseFragment<FragmentMusicListBinding>(R.layout.fragment_music_list) {
    override fun init() {
        // 툴바 버튼 생성
        setHasOptionsMenu(true)
    }

    // 툴바 버튼 생성
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_toolbar, menu)
    }

    // 툴바 버튼 클릭 이벤트
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.searchButton -> {

            }
            R.id.orderButton -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }
}