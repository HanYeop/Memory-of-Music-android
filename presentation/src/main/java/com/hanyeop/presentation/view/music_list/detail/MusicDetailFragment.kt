package com.hanyeop.presentation.view.music_list.detail

import android.app.AlertDialog
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseFragment
import com.hanyeop.presentation.databinding.FragmentMusicDetailBinding
import com.hanyeop.presentation.utils.TAG
import com.hanyeop.presentation.view.music_list.MusicViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MusicDetailFragment : BaseFragment<FragmentMusicDetailBinding>(R.layout.fragment_music_detail) {

    private val args by navArgs<MusicDetailFragmentArgs>()
    private val musicViewModel by viewModels<MusicViewModel>()

    override fun init() {
        binding.apply {
            music = args.music
            toolbar.inflateMenu(R.menu.menu_option)
        }
        initClickListener()
    }

    private fun initClickListener(){
        binding.apply {
            toolbar.apply {
                setNavigationOnClickListener {
                    findNavController().popBackStack()
                }
                setOnMenuItemClickListener { item ->
                    when(item?.itemId){
                        R.id.menu_modify -> {
                            Log.d(TAG, "initClickListener: 1")
                        }
                        R.id.menu_delete ->{
                            showDialog()
                        }
                    }
                    false
                }
            }
        }
    }

    private fun showDialog(){
        val builder = AlertDialog.Builder(requireContext())
        builder
            .setTitle(resources.getString(R.string.menu_delete))
            .setMessage(resources.getString(R.string.delete_content))
            .setPositiveButton(resources.getString(R.string.ok)) { _ , _ ->

            }
            .setNegativeButton(resources.getString(R.string.cancel)){ _, _ ->

            }
            .create()
            .show()
    }
}