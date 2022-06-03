package com.hanyeop.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

// 상태를 유지해야 하는 Fragment
abstract class BaseFragmentMain<T: ViewDataBinding>(
    @LayoutRes val layoutResId: Int
) : Fragment(){
    private var _binding: T? = null
    protected val binding get() = _binding!!

    // 뷰 최초 생성시에만 그리도록 함
    private var isInitialized = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 뷰 최초 생성시에만 그리도록 함
        if(_binding == null) _binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        if (!isInitialized) {
            init()
            isInitialized = true
        }
    }

    protected abstract fun init()
}