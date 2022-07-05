package com.hanyeop.presentation.view.setting.data

import android.app.AlertDialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.navigation.fragment.findNavController
import com.hanyeop.data.db.MusicDatabase
import com.hanyeop.presentation.R
import com.hanyeop.presentation.base.BaseFragment
import com.hanyeop.presentation.databinding.FragmentDataSettingBinding
import com.hanyeop.presentation.utils.LAST_BACKUP_TIME
import com.hanyeop.presentation.utils.LAST_RESTORE_TIME
import com.hanyeop.presentation.utils.LIST_TYPE
import com.hanyeop.presentation.utils.timeDetailFormatter
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
    @Inject
    lateinit var sharedPref: SharedPreferences

    private lateinit var fragmentActivity : MainActivity
    private lateinit var backup : RoomBackup

    override fun init() {
        initClickListener()
        initTime()
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
                restoreDialog()
            }
        }
    }

    private fun initTime(){
        binding.apply {
            textDataBackUpTime.text = "최근 백업 시간 : ${sharedPref.getString(LAST_BACKUP_TIME,"")}"
            textDataRestoreTime.text = "최근 복원 시간 : ${sharedPref.getString(LAST_RESTORE_TIME,"")}"
        }
    }

    private fun restoreDialog(){
        val builder = AlertDialog.Builder(requireContext())
        builder
            .setTitle(requireContext().resources.getString(R.string.data_restore))
            .setMessage(requireContext().resources.getString(R.string.data_restore_detail))
            .setPositiveButton(requireContext().resources.getString(R.string.ok)) { _, _ ->
                roomRestore()
            }
            .setNegativeButton(requireContext().getString(R.string.cancel)){ _, _ ->
            }
            .create()
            .show()
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
                    if (success) {
                        sharedPref.edit().putString(LAST_BACKUP_TIME, timeDetailFormatter(System.currentTimeMillis())).apply()
                        Handler(Looper.getMainLooper()).postDelayed({
                            restartApp(Intent(requireContext(), LockActivity::class.java))
                        }, 500)
                    }
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
                    sharedPref.edit().putString(LAST_RESTORE_TIME, timeDetailFormatter(System.currentTimeMillis())).apply()
                    Handler(Looper.getMainLooper()).postDelayed({
                        restartApp(Intent(requireContext(), LockActivity::class.java))
                    }, 500)
                }
            }.restore()
    }
}