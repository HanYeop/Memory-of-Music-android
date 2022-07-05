package com.hanyeop.presentation.view.setting.data

import android.content.Intent
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.navigation.fragment.findNavController
import com.ebner.roomdatabasebackup.core.RoomBackup
import com.hanyeop.data.db.MusicDatabase
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseFragment
import com.hanyeop.presentation.databinding.FragmentDataSettingBinding
import com.hanyeop.presentation.utils.SECRET_PASSWORD
import com.hanyeop.presentation.view.lock.LockActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.system.exitProcess

@AndroidEntryPoint
class DataSettingFragment : BaseFragment<FragmentDataSettingBinding>(R.layout.fragment_data_setting) {

    @Inject
    lateinit var database: MusicDatabase

    override fun init() {
        initClickListener()
    }

    private fun initClickListener(){
        binding.apply {
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            textDataBackUp.setOnClickListener {
                roomBackup()
            }
            textDataRestore.setOnClickListener {
                roomRestore()
            }
        }
    }

    private fun roomBackup(){
        RoomBackup()
            .context(requireContext())
            .database(database)
            .enableLogDebug(true)
            .backupIsEncrypted(true)
            .customEncryptPassword(SECRET_PASSWORD)
            .useExternalStorage(true)
            .maxFileCount(1)
            .apply {
                onCompleteListener { success, _ ->
                    if(success){
                        showToast("백업 완료")
                        appRestart()
                    }
                }
            }
            .backup()
    }

    private fun roomRestore(){
        RoomBackup()
            .context(requireContext())
            .database(database)
            .enableLogDebug(true)
            .backupIsEncrypted(true)
            .customEncryptPassword(SECRET_PASSWORD)
            .useExternalStorage(true)
            .maxFileCount(1)
            .apply {
                onCompleteListener { success, _ ->
                    if(success) {
                        showToast("복원 완료")
                        appRestart()
                    }
                }
            }
            .restore()
    }

    private fun appRestart(){
        finishAffinity(requireActivity())
        val intent = Intent(requireContext(), LockActivity::class.java)
        startActivity(intent)
        exitProcess(0)
    }
}