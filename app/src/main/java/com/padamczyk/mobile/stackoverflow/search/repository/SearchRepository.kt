package com.padamczyk.mobile.stackoverflow.search.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.padamczyk.mobile.stackoverflow.common.model.Question
import com.padamczyk.mobile.stackoverflow.common.repository.StackoverflowApi
import com.padamczyk.mobile.stackoverflow.common.utils.LoadingState

class SearchRepository(private val stackoverflowApi: StackoverflowApi) {
    val loadingState = MutableLiveData<LoadingState>()

    fun search(queryFilter: String): LiveData<PagedList<Question>> {
        return LivePagedListBuilder(
                QuestionsDataSource.getFactory(stackoverflowApi, queryFilter, loadingState),
                StackoverflowApi.PAGE_SIZE).build()
    }


}