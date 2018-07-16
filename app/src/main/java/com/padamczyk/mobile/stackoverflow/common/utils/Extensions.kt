package com.padamczyk.mobile.stackoverflow.common.utils

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

private val dateFormat = SimpleDateFormat("MMM dd'' yy", Locale.getDefault())

fun ViewGroup.inflate(@LayoutRes layout: Int): View {
    return LayoutInflater.from(context).inflate(layout, this, false)
}

fun ImageView.load(url: String) {
    Glide.with(this).load(url).into(this)
}

fun Long.formatDate(): String = dateFormat.format(Date(this))
