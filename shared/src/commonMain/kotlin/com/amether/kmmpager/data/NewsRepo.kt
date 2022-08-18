package com.amether.kmmpager.data

import com.amether.kmmpager.model.Article
import com.amether.kmmpager.model.ArticlesResponseDto
import com.amether.kmmpager.model.toArticle

class NewsRepo(private val httpClientFactory: HttpClientFactory) {

    suspend fun loadNews(url: String): List<Article> {
        return httpClientFactory.get<ArticlesResponseDto>(url).articles.map { it.toArticle() }
    }
}