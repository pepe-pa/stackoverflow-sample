package com.padamczyk.mobile.stackoverflow.common.utils

import android.os.Build
import android.support.annotation.LayoutRes
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.fasterxml.jackson.databind.ObjectMapper
import com.padamczyk.mobile.stackoverflow.common.model.Error
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import java.util.concurrent.TimeUnit


fun<T> Call<T>.safeExecute(): Response<T> {
    return try {
        execute()
    } catch (e: Exception) {

        val error = ObjectMapper().
                writeValueAsString(Error(400, e.localizedMessage, e.cause.toString()))

        Response.error<T>(400,
                ResponseBody.create(null, error))
    }

}

fun ViewGroup.inflate(@LayoutRes layout: Int): View {
    return LayoutInflater.from(context).inflate(layout, this, false)
}

fun ImageView.load(url: String) {
    Glide.with(this).load(url).into(this)
}

fun Long.secondsAsMillis() = TimeUnit.SECONDS.toMillis(this)

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide(visibilityLevel: Int = View.GONE) {
    visibility = visibilityLevel
}

fun String.fromHtml(): Spanned? {
    return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, 0)
    } else {
        Html.fromHtml(this)
    }
}