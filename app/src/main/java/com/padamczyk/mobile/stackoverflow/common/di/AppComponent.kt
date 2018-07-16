package com.padamczyk.mobile.stackoverflow.common.di

import com.padamczyk.mobile.stackoverflow.StackoverflowApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
    NetworkModule::class,
    ActivityBuilder::class])
interface AppComponent : AndroidInjector<DaggerApplication> {

    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: StackoverflowApp): AppComponent.Builder

        fun build(): AppComponent
    }
}