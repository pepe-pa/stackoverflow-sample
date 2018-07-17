package com.padamczyk.mobile.stackoverflow.common.repository

import com.padamczyk.mobile.stackoverflow.BuildConfig
import com.padamczyk.mobile.stackoverflow.common.model.Answer
import com.padamczyk.mobile.stackoverflow.common.model.Posts
import com.padamczyk.mobile.stackoverflow.common.model.Question
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StackoverflowApi {

    companion object {
        const val PAGE_SIZE = 20
    }

    @GET("2.2/questions/{id}/answers?" +
            "order=desc&sort=creation&site=stackoverflow&filter=withbody&" +
            "pagesize=$PAGE_SIZE&key=${BuildConfig.STACKOVERFLOW_API_KEY}")

    fun getAnswers(@Path("id") questionId: Long, @Query("page") int: Int):
            Call<Posts<Answer>>

    @GET("2.2/search/advanced?" +
            "order=desc&sort=activity&site=stackoverflow&filter=withbody&" +
            "pagesize=$PAGE_SIZE&key=${BuildConfig.STACKOVERFLOW_API_KEY}")
    fun searchQuestions(@Query("page") int: Int, @Query("q") query: String):
            Call<Posts<Question>>
}