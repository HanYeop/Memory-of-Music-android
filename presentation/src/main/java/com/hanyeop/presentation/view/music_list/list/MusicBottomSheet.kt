package com.hanyeop.presentation.view.music_list.list

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hanyeop.domain.model.music.Music
import com.hanyeop.presentation.R
import com.hanyeop.presentation.databinding.DialogMusicBottomSheetBinding
import com.hanyeop.presentation.utils.showDeleteDialog
import com.hanyeop.presentation.view.music_list.MusicViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MusicBottomSheet(private val music: Music): BottomSheetDialogFragment() {

    private var _binding: DialogMusicBottomSheetBinding? = null
    val binding get() = _binding!!

    private val musicViewModel by activityViewModels<MusicViewModel>()

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
            textStart.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query=${music.artist} ${music.title}"))
                requireContext().startActivity(intent)
                dismiss()
            }
            textDelete.setOnClickListener {
                showDeleteDialog(requireContext()){ dialogPositiveButtonClicked() }
            }
            textModify.setOnClickListener {
                musicViewModel.setMusic(music)
                findNavController().navigate(R.id.action_mainFragment_to_musicModifyFragment)
                dismiss()
            }
            textCancel.setOnClickListener {
                dismiss()
            }
        }
    }

    private fun dialogPositiveButtonClicked(){
        musicViewModel.deleteMusic(music.id)
        Toast.makeText(requireContext(), (resources.getString(R.string.delete_success)), Toast.LENGTH_SHORT).show()
        dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}