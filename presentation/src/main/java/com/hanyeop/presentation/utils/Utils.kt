package com.hanyeop.presentation.utils

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Point
import android.os.Build
import android.view.WindowManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.hanyeop.presentation.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

// 다이얼로그 사이즈 조절
fun Context.dialogResize(dialog: Dialog, width: Float, height: Float){
    val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

    if (Build.VERSION.SDK_INT < 30){
        val display = windowManager.defaultDisplay
        val size = Point()

        display.getSize(size)

        val window = dialog.window

        val x = (size.x * width).toInt()
        val y = (size.y * height).toInt()

        window?.setLayout(x, y)

    }else{
        val rect = windowManager.currentWindowMetrics.bounds

        val window = dialog.window
        val x = (rect.width() * width).toInt()
        val y = (rect.height() * height).toInt()

        window?.setLayout(x, y)
    }
}

fun timeFormatter(time: Long): String{
    val date = Date(time)
    val dateFormat = SimpleDateFormat("yyyy. MM. dd")

    return dateFormat.format(date)
}

fun timeDetailFormatter(time: Long): String{
    val date = Date(time)
    val dateFormat = SimpleDateFormat("yyyy. MM. dd HH:mm")

    return dateFormat.format(date)
}

fun showDeleteDialog(context: Context, logic: () -> Unit){
    val builder = AlertDialog.Builder(context)
    builder
        .setTitle(context.resources.getString(R.string.menu_delete))
        .setMessage(context.resources.getString(R.string.delete_content))
        .setPositiveButton(context.resources.getString(R.string.ok)) { _, _ ->
            logic()
        }
        .setNegativeButton(context.getString(R.string.cancel)){ _, _ ->
        }
        .create()
        .show()
}

fun LifecycleOwner.repeatOnStarted(block: suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED, block)
    }
}