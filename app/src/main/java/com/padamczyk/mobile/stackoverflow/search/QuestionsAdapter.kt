package com.padamczyk.mobile.stackoverflow.search

import android.arch.paging.PagedListAdapter
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.view.View
import android.view.ViewGroup
import com.padamczyk.mobile.stackoverflow.R
import com.padamczyk.mobile.stackoverflow.common.model.Question
import com.padamczyk.mobile.stackoverflow.common.utils.*
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.author_layout.*
import kotlinx.android.synthetic.main.question_list_item_layout.*

class QuestionsAdapter(val onClick: (Question) -> Unit) :
        PagedListAdapter<Question, QuestionsAdapter.QuestionViewHolder>(QuestionDiffItemCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        return QuestionViewHolder(parent.inflate(R.layout.question_list_item_layout))
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }


    class QuestionViewHolder(override val containerView: View) :
            RecyclerView.ViewHolder(containerView), LayoutContainer {

        private val tagBackground by lazy {
            ContextCompat.getColor(containerView.context, R.color.tagsBackgroundColor)
        }

        fun bind(item: Question?, onClick: (Question) -> Unit) {
            item?.run {
                containerView.setOnClickListener {
                    onClick(item)
                }
                questionTitle.text = title.fromHtml()
                votes.text = "$score"
                authorName.text = owner.display_name.fromHtml()
                authorReputation.text = "${owner.reputation}"
                with(answer_count) {
                    answers.visibility = if (this > 0) View.VISIBLE else View.INVISIBLE
                    answers.text = "${this}"
                }
                answered_date.text = containerView.context.getString(R.string.asked,
                        DateUtils.getRelativeTimeSpanString(creation_date.secondsAsMillis()))
                owner.profile_image?.let {
                    authorPicture.load(it)
                }

                tagsLayout.text = tags.asSpan(tagBackground)
            }
        }
    }
}