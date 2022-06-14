package com.hanyeop.presentation.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _listViewType = MutableSharedFlow<String>()
    val listViewType = _listViewType.asSharedFlow()

    fun setListViewType(){
        viewModelScope.launch {
            _listViewType.emit("타입 변경")
        }
    }
}