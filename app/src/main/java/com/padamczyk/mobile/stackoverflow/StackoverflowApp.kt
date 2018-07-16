package com.padamczyk.mobile.stackoverflow

import com.padamczyk.mobile.stackoverflow.common.di.DaggerAppComponent
import dagger.android.support.DaggerApplication

class StackoverflowApp : DaggerApplication() {


    override fun applicationInjector() = DaggerAppComponent.builder().
            application(this).
            build()
}