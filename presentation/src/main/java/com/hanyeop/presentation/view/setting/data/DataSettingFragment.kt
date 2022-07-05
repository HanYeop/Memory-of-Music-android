package com.hanyeop.presentation.view.setting.data

import android.content.Intent
import android.util.Log
import androidx.navigation.fragment.findNavController
import com.hanyeop.data.db.MusicDatabase
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseFragment
import com.hanyeop.presentation.databinding.FragmentDataSettingBinding
import com.hanyeop.presentation.view.MainActivity
import com.hanyeop.presentation.view.lock.LockActivity
import dagger.hilt.android.AndroidEntryPoint
import de.raphaelebner.roomdatabasebackup.core.RoomBackup
import java.io.File
import javax.inject.Inject

@AndroidEntryPoint
class DataSettingFragment : BaseFragment<FragmentDataSettingBinding>(R.layout.fragment_data_setting) {

    @Inject
    lateinit var database: MusicDatabase

    private lateinit var fragmentActivity : MainActivity
    private lateinit var backup : RoomBackup

    override fun init() {
        initClickListener()
        fragmentActivity = (activity as MainActivity)
        backup = fragmentActivity.backup
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
        backup
            .backupLocation(RoomBackup.BACKUP_FILE_LOCATION_CUSTOM_DIALOG)
            .backupLocationCustomFile(File("${requireContext().filesDir}/databasebackup/geilesBackup.sqlite3"))
            .database(database)
            .enableLogDebug(true)
            .maxFileCount(5).apply {
                onCompleteListener { success, message, exitCode ->
                    Log.d("test5", "success: $success, message: $message, exitCode: $exitCode")
                    showToast("데이터 백업이 완료되었습니다.")
                    if (success) restartApp(Intent(requireContext(), LockActivity::class.java))
                }
            }.backup()
    }

    private fun roomRestore(){
        backup
            .backupLocation(RoomBackup.BACKUP_FILE_LOCATION_CUSTOM_DIALOG)
            .backupLocationCustomFile(File("${requireContext().filesDir}/databasebackup/geilesBackup.sqlite3"))
            .database(database)
            .enableLogDebug(true)
            .apply {
                onCompleteListener { success, message, exitCode ->
                    Log.d("test5", "success: $success, message: $message, exitCode: $exitCode")
                    showToast("데이터 복원이 완료되었습니다.")
                    if (success) restartApp(Intent(requireContext(), LockActivity::class.java))
                }
            }.restore()
    }
}