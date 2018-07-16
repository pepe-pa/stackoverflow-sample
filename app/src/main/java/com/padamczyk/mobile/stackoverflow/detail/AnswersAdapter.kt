package com.padamczyk.mobile.stackoverflow.detail

import android.arch.paging.PagedListAdapter
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.padamczyk.mobile.stackoverflow.R
import com.padamczyk.mobile.stackoverflow.common.model.Answer
import com.padamczyk.mobile.stackoverflow.common.utils.inflate
import kotlinx.android.extensions.LayoutContainer

class AnswersAdapter : PagedListAdapter<Answer, AnswersAdapter.AnswerViewHolder>(AnswerDiffItemCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        return AnswerViewHolder(parent.inflate(R.layout.answear_list_item_layout))
    }

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class AnswerViewHolder(override val containerView: View) :
            RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(item: Answer?) {

        }
    }
}