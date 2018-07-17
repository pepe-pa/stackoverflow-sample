package com.padamczyk.mobile.stackoverflow.detail


import android.arch.paging.PageKeyedDataSource
import com.padamczyk.mobile.stackoverflow.common.model.Answer
import com.padamczyk.mobile.stackoverflow.common.model.Posts
import com.padamczyk.mobile.stackoverflow.common.repository.StackoverflowApi
import com.padamczyk.mobile.stackoverflow.common.utils.safeExecute

class DetailDataSource(private val api: StackoverflowApi, private val questionId: Long) :
        PageKeyedDataSource<Int, Answer>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Answer>) {
        val response: Posts<Answer>? = api.getAnswers(questionId, 1).safeExecute().body()


        response?.let {
            callback.onResult(it.items, 1, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Answer>) {
        val response: Posts<Answer>? = api.getAnswers(questionId, params.key).safeExecute().body()

        response?.let {
            callback.onResult(it.items, params.key + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Answer>) {
        val response: Posts<Answer>? = api.getAnswers(questionId, params.key - 1).safeExecute().body()

        response?.let {
            callback.onResult(it.items, params.key - 1)
        }
    }

}