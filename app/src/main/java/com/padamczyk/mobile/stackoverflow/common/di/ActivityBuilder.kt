package com.padamczyk.mobile.stackoverflow.common.di

import com.padamczyk.mobile.stackoverflow.detail.view.DetailActivity
import com.padamczyk.mobile.stackoverflow.detail.di.DetailModule
import com.padamczyk.mobile.stackoverflow.search.view.SearchActivity
import com.padamczyk.mobile.stackoverflow.search.di.SearchModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(SearchModule::class)])
    abstract fun searchActivity() : SearchActivity

    @ContributesAndroidInjector(modules = [(DetailModule::class)])
    abstract fun detailActivity(): DetailActivity
}