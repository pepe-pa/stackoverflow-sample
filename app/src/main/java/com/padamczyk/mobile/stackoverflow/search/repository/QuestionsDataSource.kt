package com.padamczyk.mobile.stackoverflow.search.repository

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import android.arch.paging.PageKeyedDataSource
import android.util.Log
import com.fasterxml.jackson.databind.ObjectMapper
import com.padamczyk.mobile.stackoverflow.common.model.Posts
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
            Log.e("tag", response.raw().code().toString())

            response.errorBody().handleError()

        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Question>) {
        val response: Posts<Question>? = api.searchQuestions(params.key, query).safeExecute().body()

        response?.let {
            callback.onResult(it.items, params.key + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Question>) {
        val response: Posts<Question>? = api.searchQuestions(1, query).safeExecute().body()

        response?.let {
            callback.onResult(it.items, params.key - 1)
        }
    }


    private fun ResponseBody?.handleError() {
        this?.bytes()?.let {
            var errorJson = String(it)
            Log.e(TAG, errorJson)
            var error = ObjectMapper().readValue(String(it), com.padamczyk.mobile.stackoverflow.common.model.Error::class.java)
            Log.e(TAG, error.errorMessage)
            loadingState.postValue(ErrorOccurs(error.errorMessage))
        }
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