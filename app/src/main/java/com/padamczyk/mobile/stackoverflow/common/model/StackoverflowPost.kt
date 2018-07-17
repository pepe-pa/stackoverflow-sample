package com.padamczyk.mobile.stackoverflow.common.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

interface StackoverflowPost

@Parcelize
data class Question(
        var tags: List<String>,
        var owner: Owner,
        var is_answered: Boolean,
        var view_count: Int,
        var answer_count: Int,
        var score: Int,
        var last_activity_date: Long,
        var creation_date: Long,
        var last_edit_date: Long,
        var question_id: Long,
        var link: String,
        var title: String,
        var body: String,
        var closed_date: Long,
        var closed_reason: String?,
        var accepted_answer_id: Long
) : StackoverflowPost, Parcelable

@Parcelize
data class Answer(
        var owner: Owner,
        var is_accepted: Boolean,
        var score: Int,
        var last_activity_date: Long,
        var creation_date: Long,
        var answer_id: Int,
        var question_id: Int,
        var body: String
) : StackoverflowPost, Parcelable