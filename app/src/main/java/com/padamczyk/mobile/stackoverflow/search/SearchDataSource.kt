package com.padamczyk.mobile.stackoverflow.search

import android.arch.paging.PageKeyedDataSource
import android.util.Log
import com.fasterxml.jackson.databind.ObjectMapper
import com.padamczyk.mobile.stackoverflow.common.model.Posts
import com.padamczyk.mobile.stackoverflow.common.model.Question
import com.padamczyk.mobile.stackoverflow.common.repository.StackoverflowApi
import okhttp3.ResponseBody

class SearchDataSource(private val api: StackoverflowApi,
                       private val query: String) : PageKeyedDataSource<Int, Question>() {


    private val TAG = "SearchDataSource"

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Question>) {
        val response = api.searchQuestions(1, query).execute()
        if (response.isSuccessful) {
            response.body()?.let {
                callback.onResult(it.items, 1, 2)
            }
        } else {
            Log.e("tag", response.raw().code().toString())

            response.errorBody().handleError()

        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Question>) {
        val response: Posts<Question>? = api.searchQuestions(params.key, query).execute().body()

        response?.let {
            callback.onResult(it.items, params.key + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Question>) {
        val response: Posts<Question>? = api.searchQuestions(1, query).execute().body()

        response?.let {
            callback.onResult(it.items, params.key - 1)
        }
    }



    private fun ResponseBody?.handleError() {
        this?.bytes()?.let {
            var errorJson = String(it)
            Log.e("tag", errorJson)
            var error = ObjectMapper().readValue(String(it), Error::class.java)
            Log.e(TAG, error.message )

        }
    }
}