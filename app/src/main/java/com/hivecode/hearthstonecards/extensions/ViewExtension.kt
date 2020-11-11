package com.hivecode.hearthstonecards.extensions

import android.view.View
import android.widget.ImageView

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

//fun ImageView.loadUrl(url: String) {
//    Picasso.get()
//        .load(url)
//        .placeholder(R.drawable.ic_load)
//        .error(R.drawable.ic_round_account_circle)
//        .into(this)
//}