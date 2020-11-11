package com.hivecode.hearthstonecards.extensions

import android.view.View
import android.widget.ImageView
import com.hivecode.hearthstonecards.R
import com.squareup.picasso.Picasso

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun ImageView.loadUrl(url: String) {
    Picasso.get()
        .load(url)
        .placeholder(R.drawable.card_sample)
        .error(R.drawable.card_sample)
        .into(this)
}