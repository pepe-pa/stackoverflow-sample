package com.padamczyk.mobile.stackoverflow.detail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import com.padamczyk.mobile.stackoverflow.common.model.Answer

class DetailViewModel(private val detailRepository: DetailRepository) : ViewModel() {

    private val questionId : MutableLiveData<Long> = MutableLiveData()
    val answers : LiveData<PagedList<Answer>> = Transformations.switchMap(questionId) {
        detailRepository.getAnswers(it)
    }

    fun fetchAnswers(id: Long) {
        questionId.value = id
    }



}