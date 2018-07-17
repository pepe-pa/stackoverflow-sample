package com.padamczyk.mobile.stackoverflow.detail.di

import android.arch.lifecycle.ViewModelProvider
import com.padamczyk.mobile.stackoverflow.common.repository.StackoverflowApi
import com.padamczyk.mobile.stackoverflow.common.viewmodel.ViewModelFactory
import com.padamczyk.mobile.stackoverflow.detail.repository.DetailRepository
import com.padamczyk.mobile.stackoverflow.detail.viewmodel.DetailViewModel
import dagger.Module
import dagger.Provides

@Module
class DetailModule {

    @Provides
    fun providesRepository(stackoverflowApi: StackoverflowApi) = DetailRepository(stackoverflowApi)

    @Provides
    fun providesViewModel(detailRepository: DetailRepository) = DetailViewModel(detailRepository)

    @Provides
    fun providesViewModelFactory(detailViewModel: DetailViewModel): ViewModelProvider.Factory {
        return ViewModelFactory(detailViewModel)
    }
}