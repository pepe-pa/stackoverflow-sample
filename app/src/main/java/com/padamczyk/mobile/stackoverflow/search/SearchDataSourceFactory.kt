package com.padamczyk.mobile.stackoverflow.search

import android.arch.paging.DataSource
import com.padamczyk.mobile.stackoverflow.common.model.Question
import com.padamczyk.mobile.stackoverflow.common.repository.StackoverflowApi

class SearchDataSourceFactory(private val stackoverflowApi: StackoverflowApi,
                              private val query: String) : DataSource.Factory<Int, Question>() {
    override fun create(): DataSource<Int, Question> {
        return SearchDataSource(stackoverflowApi, query)
    }
}