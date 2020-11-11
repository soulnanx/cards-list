package com.hivecode.hearthstonecards.base

import androidx.appcompat.app.AppCompatActivity
import com.hivecode.hearthstonecards.R
import com.hivecode.hearthstonecards.extensions.loading

open class BaseActivity : AppCompatActivity() {

    private val loadingDialog by lazy {
        loading { messageRes = R.string.loading_text }.build()
    }

    fun showLoading() {
        loadingDialog.show()
    }

    fun hideLoading() {
        loadingDialog.dismiss()
    }
}
