package com.padamczyk.mobile.stackoverflow.search.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import com.padamczyk.mobile.stackoverflow.common.model.Question
import com.padamczyk.mobile.stackoverflow.search.repository.SearchRepository
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch


class SearchViewModel(private val searchRepository: SearchRepository) : ViewModel() {

    private val query = MutableLiveData<String>()
    val loadingState = searchRepository.loadingState

    val questions: LiveData<PagedList<Question>> = Transformations.switchMap(query) {
        searchRepository.search(it)
    }

    private var job: Job? = null

    fun search(queryFilter: String?) {
        if (!queryFilter.isNullOrEmpty()) {
            job?.run {
                cancel()
            }
            launch {
                job = launch {
                    delay(500)
                    if(query.value != queryFilter) {
                        query.postValue(queryFilter)
                    }
                }
            }
        }


    }
}
