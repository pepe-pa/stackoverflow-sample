package com.padamczyk.mobile.stackoverflow.search.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.SearchView
import android.util.Log
import com.padamczyk.mobile.stackoverflow.R
import com.padamczyk.mobile.stackoverflow.common.model.Question
import com.padamczyk.mobile.stackoverflow.common.utils.*
import com.padamczyk.mobile.stackoverflow.detail.view.DetailActivity
import com.padamczyk.mobile.stackoverflow.search.viewmodel.SearchViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.search_activity.*
import javax.inject.Inject

class SearchActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProviders.of(this, factory).get(SearchViewModel::class.java)
    }

    private val adapter by lazy {
        QuestionsAdapter(this::onItemClicked)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity)

        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener, android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.search(newText)
                return true
            }

        })

        viewModel.loadingState.observe(this, Observer {
            when(it) {
                is Init -> {
                    progress.hide()
                    recyclerView.hide()
                    status.show()
                    status.setText(it.text)
                    status.setCompoundDrawablesWithIntrinsicBounds(0, it.drawable, 0, 0)
                }
                is InProgress -> {
                    progress.show()
                    recyclerView.hide()
                    status.hide()
                }
                is ErrorOccurs -> {
                    progress.hide()
                    recyclerView.hide()
                    status.show()
                    status.text = it.message
                    status.setCompoundDrawablesWithIntrinsicBounds(0, it.drawable, 0, 0)
                }
                is Done -> {
                    progress.hide()
                    recyclerView.show()
                    status.hide()
                }
            }
        })

        viewModel.questions.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    private fun onItemClicked(item: Question) {
        Log.i("tag", "$item")
        val intent = Intent(this, DetailActivity::class.java)
        with(Bundle()) {
            putParcelable(DetailActivity.QUESTION_KEY, item)
            intent.putExtras(this)
        }

        startActivity(intent)
    }
}