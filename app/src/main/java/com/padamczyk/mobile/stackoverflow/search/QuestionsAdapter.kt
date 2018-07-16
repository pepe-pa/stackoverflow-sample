package com.padamczyk.mobile.stackoverflow.search

import android.arch.paging.PagedListAdapter
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.View
import android.view.ViewGroup
import com.padamczyk.mobile.stackoverflow.R
import com.padamczyk.mobile.stackoverflow.common.model.Question
import com.padamczyk.mobile.stackoverflow.common.utils.formatDate
import com.padamczyk.mobile.stackoverflow.common.utils.inflate
import com.padamczyk.mobile.stackoverflow.common.utils.load
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.author_layout.*
import kotlinx.android.synthetic.main.question_list_item_layout.*

class QuestionsAdapter : PagedListAdapter<Question, QuestionsAdapter.QuestionViewHolder>(QuestionDiffItemCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        return QuestionViewHolder(parent.inflate(R.layout.question_list_item_layout))
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class QuestionViewHolder(override val containerView: View) :
            RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(item: Question?) {
            item?.let {
                title.text = Html.fromHtml(item.title)
                votes.text = "${item.score}"
                authorName.text = item.owner.display_name
                authorReputation.text = "${item.owner.reputation}"
                with(item.answer_count) {
                    answers.visibility = if (this > 0) View.VISIBLE else View.INVISIBLE
                    answers.text = "${this}"
                }
                answered_date.text = item.creation_date.formatDate()
                item.owner.profile_image?.let {
                    authorPicture.load(it)
                }
            }
        }
    }
}

