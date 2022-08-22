package com.amether.kmmpager.data

import com.amether.kmmpager.domain.NewsApi
import com.amether.kmmpager.domain.NewsRepo
import com.amether.kmmpager.model.Article
import dev.icerock.moko.mvvm.livedata.asFlow
import dev.icerock.moko.mvvm.livedata.data
import dev.icerock.moko.paging.LambdaPagedListDataSource
import dev.icerock.moko.paging.Pagination
import io.github.aakira.napier.Napier
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel(
    private val newsRepo: NewsRepo,
    private val newsApi: NewsApi
) {

    private val _query = MutableStateFlow("")

    private val pagination: Pagination<Article> = Pagination(
        parentScope = MainScope(),
        dataSource = LambdaPagedListDataSource {
            newsRepo.loadNews(newsApi.concatUrl(
                query = _query.value,
                page = it?.size ?: 1
            ))
        },
        comparator = Comparator { a: Article, b: Article ->
            a.content!!.compareTo(b.content!!)
        },
        nextPageListener = { result: Result<List<Article>> ->
            if (result.isFailure) {
                Napier.i("Ошибка при загрузке: ${result.exceptionOrNull()}")
            }
        },
        refreshListener = {result: Result<List<Article>> ->
            if (result.isFailure) {
                Napier.i("Ошибка при обновлении")
            }
        }
    )

    fun dataFlow() = pagination.state.data().asFlow()

    fun loadNextPage() {
        pagination.loadNextPage()
    }

    fun onQueryChange(query: String) {
        _query.value = query
        pagination.loadFirstPage()
    }
}