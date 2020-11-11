package com.hivecode.hearthstonecards.extensions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.hivecode.hearthstonecards.R
import kotlinx.android.synthetic.main.loading_dialog.view.*

fun Context.loading(init: CustomLoadingDialog.() -> Unit): CustomLoadingDialog {
    return CustomLoadingDialog(this).apply { init() }
}

class CustomLoadingDialog(
    private val context: Context
) {
    var messageRes: Int = 0

    private fun getView(): View {
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return layoutInflater.inflate(R.layout.loading_dialog, null).apply {
            label.setText(messageRes)
        } ?: View(context)
    }

    private fun buildDialog(): AlertDialog = MaterialAlertDialogBuilder(context)
        .setView(getView())
        .setCancelable(false)
        .create()

    fun show() = buildDialog().apply { show() }
    fun build() = buildDialog()
}