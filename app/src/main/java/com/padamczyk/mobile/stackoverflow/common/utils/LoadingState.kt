package com.padamczyk.mobile.stackoverflow.common.utils

import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import com.padamczyk.mobile.stackoverflow.R

sealed class LoadingState

class Init : LoadingState() {

    @StringRes
    val text: Int = R.string.init_state_text
    @DrawableRes
    val drawable: Int = R.drawable.ic_search
}


class InProgress : LoadingState()

class Done : LoadingState()

class ErrorOccurs(val message: String) : LoadingState() {

    @DrawableRes
    val drawable  = R.drawable.ic_error
}