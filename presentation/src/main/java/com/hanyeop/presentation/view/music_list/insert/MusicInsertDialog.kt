package com.hanyeop.presentation.view.music_list.insert

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.hanyeop.domain.model.music.DomainMusicResponse
import com.hanyeop.domain.model.music.Music
import com.hanyeop.presentation.R
import com.hanyeop.presentation.databinding.DialogMusicInsertBinding
import com.hanyeop.presentation.utils.dialogResize

class MusicInsertDialog(context: Context, private val listener: MusicInsertDialogListener,
                        private val musicInfo: DomainMusicResponse): Dialog(context) {

    private lateinit var binding: DialogMusicInsertBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.dialog_music_insert,
            null,false
        )
        setContentView(binding.root)

        context.dialogResize(this,0.9f,0.9f)

        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.musicInfo = musicInfo

        initClickListener()
    }

    private fun initClickListener(){
        binding.apply {
            btnOk.setOnClickListener {
                if(editTitle.text!!.isNotBlank() && editArtist.text!!.isNotBlank()
                    && editSummary.text!!.isNotBlank() && editContent.text!!.isNotBlank()) {
                    listener.onOkButtonClicked(
                        Music(
                            image = musicInfo!!.image,
                            title = editTitle.text.toString(),
                            artist = editArtist.text.toString(),
                            rating = 5.0f,
                            summary = editSummary.text.toString(),
                            content = editContent.text.toString()
                        )
                    )
                    dismiss()
                } else{
                    Toast.makeText(context, "입력을 다시 확인해주세요.", Toast.LENGTH_SHORT).show()
                }
            }
            btnCancel.setOnClickListener {
                dismiss()
            }
        }
    }
}