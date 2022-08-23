package com.amether.kmmpager.di

import com.amether.kmmpager.data.AuthViewModel
import com.amether.kmmpager.data.MainViewModel
import com.amether.kmmpager.data.HttpClientFactory
import com.amether.kmmpager.domain.AuthRepo
import com.amether.kmmpager.domain.NewsApi
import com.amether.kmmpager.domain.NewsRepo
import org.kodein.di.*

object DiModule {

    val mainViewModel: MainViewModel by kodein.instance()
    val authViewModel: AuthViewModel by kodein.instance()
}

internal val kodein = DI.lazy {
    bindSingleton {
        MainViewModel(
            newsRepo = instance<NewsRepo>(),
            newsApi = instance<NewsApi>()
        )
    }
    bindSingleton { AuthRepo(instance()) }
    bindSingleton { AuthViewModel(instance()) }
    bindSingleton { NewsApi }
    bindSingleton { NewsRepo(instance()) }
    bindSingleton { HttpClientFactory }
}