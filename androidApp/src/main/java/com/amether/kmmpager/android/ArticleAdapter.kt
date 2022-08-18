package com.amether.kmmpager.android

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amether.kmmpager.model.Article

class ArticleAdapter : ListAdapter<Article, RecyclerView.ViewHolder>(
    object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.content == newItem.content && oldItem.title == newItem.title
        }
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return object : RecyclerView.ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)) {}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.itemView.findViewById<TextView>(R.id.titleView).text = it.title
            holder.itemView.findViewById<TextView>(R.id.authorView).text = it.author
        }
    }
}