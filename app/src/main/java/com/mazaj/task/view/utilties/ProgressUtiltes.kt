package com.mazaj.task.view.utilties

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.WindowManager.BadTokenException
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.mazaj.task.R


//region create instance from custom Progress Dialog
fun createProgressDialog(mContext: Context?): ProgressDialog {
    val dialog = ProgressDialog(mContext)
    try {
        dialog.show()
    } catch (e: BadTokenException) {
    }
    dialog.setCancelable(false)
    dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog.setContentView(R.layout.item_progress)
    // dialog.setMessage(Message);
    return dialog
}
//endregion