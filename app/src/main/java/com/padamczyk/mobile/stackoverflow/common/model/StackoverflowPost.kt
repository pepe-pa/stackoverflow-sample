package com.padamczyk.mobile.stackoverflow.common.model

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

sealed class StackoverflowPost

@Parcelize
data class Question(
        var tags: List<String>,
        var owner: Owner,
        @JsonProperty("is_answered")
        var isAnswered: Boolean,
        @JsonProperty("answer_count")
        var answerCount: Int,
        var score: Int,
        @JsonProperty("creation_date")
        var creationDate: Long,
        @JsonProperty("question_id")
        var questionId: Long,
        var link: String,
        var title: String,
        var body: String,
        @JsonProperty("accepted_answer_id")
        var acceptedAnswerId: Long
) : StackoverflowPost(), Parcelable

@Parcelize
data class Answer(
        var owner: Owner,
        @JsonProperty("is_accepted")
        var isAccepted: Boolean,
        var score: Int,
        @JsonProperty("creation_date")
        var creationDate: Long,
        @JsonProperty("answer_id")
        var answerId: Int,
        @JsonProperty("question_id")
        var questionId: Int,
        var body: String
) : StackoverflowPost(), Parcelable