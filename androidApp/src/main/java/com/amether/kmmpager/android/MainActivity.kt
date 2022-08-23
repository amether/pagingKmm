package com.amether.kmmpager.android

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.amether.kmmpager.di.DiModule
import com.amether.kmmpager.data.MainViewModel
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel: MainViewModel = DiModule.mainViewModel
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

        val toAuthFragment = findViewById<Button>(R.id.toNextFragment)
        toAuthFragment.setOnClickListener {
//            val content = findViewById<LinearLayout>(R.id.contentLayout)
//            content.visibility = View.GONE
//            findViewById<FragmentContainerView>(R.id.fragmentContainer).visibility = View.VISIBLE
            supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, AuthFragment()).commit()
        }
    }

    companion object {
        private const val SCROLL_TO_BOTTOM = 1
    }
}
