package com.padamczyk.mobile.stackoverflow.detail

import android.arch.paging.PagedListAdapter
import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.view.View
import android.view.ViewGroup
import com.padamczyk.mobile.stackoverflow.R
import com.padamczyk.mobile.stackoverflow.common.model.Answer
import com.padamczyk.mobile.stackoverflow.common.utils.fromHtml
import com.padamczyk.mobile.stackoverflow.common.utils.inflate
import com.padamczyk.mobile.stackoverflow.common.utils.load
import com.padamczyk.mobile.stackoverflow.common.utils.secondsAsMillis
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.author_layout.*
import kotlinx.android.synthetic.main.post_item_layout.*

class AnswersAdapter : PagedListAdapter<Answer, AnswersAdapter.AnswerViewHolder>(AnswerDiffItemCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        return AnswerViewHolder(parent.inflate(R.layout.post_item_layout))
    }

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class AnswerViewHolder(override val containerView: View) :
            RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(item: Answer?) {
            item?.run {
                votes.text = "$score"
                acceptedAnswer.visibility = if (is_accepted) View.VISIBLE else View.INVISIBLE
                bodyTextView.text = body.fromHtml()
                answered_date.text = containerView.context.getString(R.string.answered,
                        DateUtils.getRelativeTimeSpanString(creation_date.secondsAsMillis()))
                owner.profile_image?.let { authorPicture.load(it) }
                authorName.text = owner.display_name.fromHtml()
                authorReputation.text = "${owner.reputation}"

            }
        }
    }
}