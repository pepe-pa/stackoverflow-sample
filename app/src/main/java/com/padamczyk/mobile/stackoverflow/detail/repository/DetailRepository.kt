package com.padamczyk.mobile.stackoverflow.detail.repository


import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.padamczyk.mobile.stackoverflow.common.model.Answer
import com.padamczyk.mobile.stackoverflow.common.repository.StackoverflowApi

class DetailRepository(private val stackoverflowApi: StackoverflowApi) {

    fun getAnswers(questionId: Long): LiveData<PagedList<Answer>> {
        return LivePagedListBuilder(AnswersDataSource.getFactory(stackoverflowApi, questionId), 10).build()
    }



}