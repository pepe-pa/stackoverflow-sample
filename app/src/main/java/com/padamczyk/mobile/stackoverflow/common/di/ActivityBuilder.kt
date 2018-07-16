package com.padamczyk.mobile.stackoverflow.common.di

import com.padamczyk.mobile.stackoverflow.search.SearchActivity
import com.padamczyk.mobile.stackoverflow.search.SearchModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(SearchModule::class)])
    abstract fun searchActivity() : SearchActivity

   /* @ContributesAndroidInjector
    abstract fun detailActivity(): Detail*/
}