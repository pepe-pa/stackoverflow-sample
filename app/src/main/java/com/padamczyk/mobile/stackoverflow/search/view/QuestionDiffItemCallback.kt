package com.padamczyk.mobile.stackoverflow.search.view

import android.support.v7.util.DiffUtil
import com.padamczyk.mobile.stackoverflow.common.model.Question

object QuestionDiffItemCallback : DiffUtil.ItemCallback<Question>() {
    override fun areItemsTheSame(oldItem: Question?, newItem: Question?): Boolean {
        return oldItem?.questionId== newItem?.questionId
    }

    override fun areContentsTheSame(oldItem: Question?, newItem: Question?): Boolean {
        return oldItem == newItem
    }
}