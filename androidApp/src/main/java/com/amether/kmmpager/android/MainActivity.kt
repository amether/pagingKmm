package com.amether.kmmpager.android

import android.os.Build
import android.os.Bundle
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.amether.kmmpager.di.DiModule
import com.amether.kmmpager.data.MainViewModel

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel: MainViewModel = DiModule.viewModel
        val recyclerView = findViewById<RecyclerView>(R.id.news)
        val query = findViewById<EditText>(R.id.query)
        query.doOnTextChanged { text, _, _, _ ->
            viewModel.onQueryChange(text.toString())
        }
        val adapter = ArticleAdapter()
        recyclerView.adapter = adapter

        recyclerView.setOnScrollChangeListener { _, _, _, _, _ ->
            if (!recyclerView.canScrollVertically(SCROLL_TO_BOTTOM)) {
                viewModel.loadNextPage()
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.dataFlow().collect{
                adapter.submitList(it)
            }
        }
    }

    companion object {
        private const val SCROLL_TO_BOTTOM = 1
    }
}
