package com.hanyeop.presentation.view.sort

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.hanyeop.presentation.R
import com.hanyeop.presentation.databinding.DialogSortBinding
import com.hanyeop.presentation.utils.dialogResize

class SortDialog(context: Context, private val listener: SortListener): Dialog(context) {

    private lateinit var binding: DialogSortBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_sort, null, false)
        setContentView(binding.root)

        context.dialogResize(this,0.6f,0.4f)

        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

//        initClickListener()
    }
}