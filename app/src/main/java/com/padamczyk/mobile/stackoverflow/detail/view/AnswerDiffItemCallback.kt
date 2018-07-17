package com.padamczyk.mobile.stackoverflow.detail.view

import android.support.v7.util.DiffUtil
import com.padamczyk.mobile.stackoverflow.common.model.Answer

object AnswerDiffItemCallback : DiffUtil.ItemCallback<Answer>() {
    override fun areItemsTheSame(oldItem: Answer?, newItem: Answer?): Boolean {
        return oldItem?.answerId == newItem?.answerId
    }

    override fun areContentsTheSame(oldItem: Answer?, newItem: Answer?): Boolean {
        return oldItem == newItem
    }
}