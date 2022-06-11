package com.hanyeop.presentation.view.music_list.list

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hanyeop.domain.model.music.Music
import com.hanyeop.presentation.R
import com.hanyeop.presentation.databinding.DialogMusicBottomSheetBinding
import com.hanyeop.presentation.view.music_list.MusicViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MusicBottomSheet(private val music: Music): BottomSheetDialogFragment() {

    private var _binding: DialogMusicBottomSheetBinding? = null
    val binding get() = _binding!!

    private val musicViewModel by viewModels<MusicViewModel>()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(),R.style.AppBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.dialog_music_bottom_sheet, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        init()
    }

    private fun init(){
        initClickListener()
    }

    private fun initClickListener(){
        binding.apply {
            textDelete.setOnClickListener {
                showDialog()
            }

            textCancel.setOnClickListener {
                dismiss()
            }
        }
    }

    private fun showDialog(){
        val builder = AlertDialog.Builder(requireContext())
        builder
            .setTitle(resources.getString(R.string.menu_delete))
            .setMessage(resources.getString(R.string.delete_content))
            .setPositiveButton(resources.getString(R.string.ok)) { _ , _ ->
                musicViewModel.deleteMusic(music.id)
                Toast.makeText(requireContext(), (resources.getString(R.string.delete_success)), Toast.LENGTH_SHORT).show()
                dismiss()
            }
            .setNegativeButton(resources.getString(R.string.cancel)){ _, _ ->

            }
            .create()
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}