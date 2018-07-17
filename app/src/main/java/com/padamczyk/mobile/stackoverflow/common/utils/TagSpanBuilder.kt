package com.padamczyk.mobile.stackoverflow.common.utils

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.BackgroundColorSpan


fun Collection<String>.asSpan(color: Int = Color.LTGRAY): Spannable {
    val stringBuilder = SpannableStringBuilder()
    forEach {
        stringBuilder.
                append(" $it ", BackgroundColorSpan(color), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE).
                append("  ")
    }

    return stringBuilder
}
