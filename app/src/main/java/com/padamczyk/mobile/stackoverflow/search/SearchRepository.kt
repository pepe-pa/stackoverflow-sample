package com.padamczyk.mobile.stackoverflow.search

import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.padamczyk.mobile.stackoverflow.common.model.Question
import com.padamczyk.mobile.stackoverflow.common.repository.StackoverflowApi

class SearchRepository(val stackoverflowApi: StackoverflowApi) {
    fun search(queryFilter: String): LiveData<PagedList<Question>> {
        return LivePagedListBuilder(
                SearchDataSourceFactory(stackoverflowApi, queryFilter), StackoverflowApi.PAGE_SIZE).
                build()
    }


}