package com.padamczyk.mobile.stackoverflow.detail.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.text.format.DateUtils
import android.view.MenuItem
import com.padamczyk.mobile.stackoverflow.R
import com.padamczyk.mobile.stackoverflow.common.model.Question
import com.padamczyk.mobile.stackoverflow.common.utils.*
import com.padamczyk.mobile.stackoverflow.detail.viewmodel.DetailViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.author_layout.*
import kotlinx.android.synthetic.main.detail_activity.*
import kotlinx.android.synthetic.main.post_item_layout.*
import javax.inject.Inject

class DetailActivity : DaggerAppCompatActivity() {

    companion object {
        const val QUESTION_KEY = "question_key"
    }

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProviders.of(this, factory).get(DetailViewModel::class.java)
    }

    private val adapter by lazy {
        AnswersAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        answersRecyclerView.adapter = adapter
        answersRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        val question: Question = intent.extras.getParcelable(QUESTION_KEY)
        setFullQuestionView(question)
        viewModel.fetchAnswers(question.questionId)

        viewModel.answers.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    private fun setFullQuestionView(question: Question) {
        with(question) {
            votes.text = "$score"
            toolbarTitle.text = title
            bodyTextView.text = body.fromHtml()
            answered_date.text = getString(R.string.asked,
                    DateUtils.getRelativeTimeSpanString(creationDate.secondsAsMillis()))
            authorName.text = owner.displayName.fromHtml()
            owner.profileImage?.let { authorPicture.load(it) }
            authorReputation.text = "${owner.reputation}"
            tagsLayout.show()
            tagsLayout.text = tags.asSpan(ContextCompat.getColor(
                    this@DetailActivity, R.color.tagsBackgroundColor))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}