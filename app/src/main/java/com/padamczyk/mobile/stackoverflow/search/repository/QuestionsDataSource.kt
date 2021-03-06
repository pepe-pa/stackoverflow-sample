package com.padamczyk.mobile.stackoverflow.search.repository

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import android.arch.paging.PageKeyedDataSource
import android.util.Log
import com.fasterxml.jackson.databind.ObjectMapper
import com.padamczyk.mobile.stackoverflow.common.model.Error
import com.padamczyk.mobile.stackoverflow.common.model.Question
import com.padamczyk.mobile.stackoverflow.common.repository.StackoverflowApi
import com.padamczyk.mobile.stackoverflow.common.utils.*
import okhttp3.ResponseBody

class QuestionsDataSource(private val api: StackoverflowApi,
                          private val query: String,
                          private val loadingState: MutableLiveData<LoadingState>)
    : PageKeyedDataSource<Int, Question>() {


    private val TAG = "QuestionsDataSource"

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Question>) {
        loadingState.postValue(InProgress())

        val response = api.searchQuestions(1, query).safeExecute()

        if (response.isSuccessful) {
            response.body()?.let {
                loadingState.postValue(if (it.items.isEmpty()) Init() else Done())
                callback.onResult(it.items, 1, 2)
            }
        } else {
            response.errorBody().handleError()
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Question>) {

        api.searchQuestions(params.key, query).safeExecute().takeIf { it.isSuccessful }?.body()?.run {
            callback.onResult(items, params.key + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Question>) {
        // Basically for current use case loadBefore method will not be called
    }


    private fun ResponseBody?.handleError() {
        this?.string()?.let {
            Log.e(TAG, it)
            val error = ObjectMapper().readValue(it, Error::class.java)
            Log.e(TAG, error.errorMessage)
            loadingState.postValue(ErrorOccurs(error.errorMessage ?: "UNKNOWN ERROR"))
        } ?: loadingState.postValue(ErrorOccurs("UNKNOWN ERROR"))
    }

    companion object {
        fun getFactory(api: StackoverflowApi,
                       query: String,
                       loadingState: MutableLiveData<LoadingState>): Factory<Int, Question> {
            return object : DataSource.Factory<Int, Question>() {
                override fun create(): DataSource<Int, Question> {
                    return QuestionsDataSource(api, query, loadingState)
                }
            }
        }
    }

}