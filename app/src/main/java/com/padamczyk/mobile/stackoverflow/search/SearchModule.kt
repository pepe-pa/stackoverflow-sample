package com.padamczyk.mobile.stackoverflow.search

import android.arch.lifecycle.ViewModelProvider
import com.padamczyk.mobile.stackoverflow.common.repository.StackoverflowApi
import com.padamczyk.mobile.stackoverflow.common.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class SearchModule {

    @Provides
    fun providesRepository(stackoverflowApi: StackoverflowApi) = SearchRepository(stackoverflowApi)

    @Provides
    fun providesViewModel(searchRepository: SearchRepository) = SearchViewModel(searchRepository)

    @Provides
    fun providesViewModelFactory(searchViewModel: SearchViewModel): ViewModelProvider.Factory {
        return ViewModelFactory(searchViewModel)
    }
}