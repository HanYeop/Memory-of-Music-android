package com.hanyeop.presentation.view.category

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.github.guilhe.views.SeekBarRangedView
import com.hanyeop.presentation.R
import com.hanyeop.presentation.databinding.DialogCategoryBinding
import com.hanyeop.presentation.utils.dialogResize
import kotlin.math.round

class CategoryDialog(context: Context, private val listener: CategoryDialogListener): Dialog(context) {

    private lateinit var binding: DialogCategoryBinding
    private val stepList = listOf(10f,20f,30f,40f,50f,60f,70f,80f,90f)
    private val div = 20
    private var min = 0f
    private var max = 5f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_category, null, false)
        setContentView(binding.root)

        context.dialogResize(this,0.8f,0.3f)

        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        initClickListener()
        initSeekBar()
    }

    private fun initClickListener(){
        binding.apply {
            btnOk.setOnClickListener {
                listener.onRatingSelected(min, max)
                dismiss()
            }
            btnCancel.setOnClickListener {
                dismiss()
            }
        }
    }

    private fun initSeekBar(){
        binding.seekBarRating.apply {
            enableProgressBySteps(true)
            setProgressSteps(stepList)
            actionCallback = object : SeekBarRangedView.SeekBarRangedChangeCallback{
                override fun onChanged(minValue: Float, maxValue: Float) {
                }

                override fun onChanging(minValue: Float, maxValue: Float) {
                    min = round(minValue * 2) / 2 / div
                    max = round(maxValue * 2) / 2 / div
                    binding.textMin.text = min.toString()
                    binding.textMax.text = max.toString()
                }
            }
        }
    }
}