package com.padamczyk.mobile.stackoverflow.search

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.padamczyk.mobile.stackoverflow.common.model.Question
import com.padamczyk.mobile.stackoverflow.common.repository.StackoverflowApi
import com.padamczyk.mobile.stackoverflow.common.utils.LoadingState

class SearchDataSourceFactory(private val stackoverflowApi: StackoverflowApi,
                              private val query: String,
                              private val loadingState: MutableLiveData<LoadingState>) : DataSource.Factory<Int, Question>() {
    override fun create(): DataSource<Int, Question> {
        return SearchDataSource(stackoverflowApi, query, loadingState)
    }
}