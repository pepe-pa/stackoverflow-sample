package com.padamczyk.mobile.stackoverflow.detail

import android.arch.paging.DataSource
import com.padamczyk.mobile.stackoverflow.common.model.Answer
import com.padamczyk.mobile.stackoverflow.common.repository.StackoverflowApi

class DetailDataSourceFactory(private val stackoverflowApi: StackoverflowApi,
                              private val questionId: Long) : DataSource.Factory<Int, Answer>() {
    override fun create(): DataSource<Int, Answer> {
        return DetailDataSource(stackoverflowApi, questionId)
    }
}