package com.amether.kmmpager.domain

object NewsApi {

    private const val baseUrl = "https://newsapi.org/v2/everything"
    private const val apiKey2 = "a3aebd96c7c94de09b552a2aee1675d4"
    private const val apiKey = "5eb0bf747eba482791fed6e9a5d78e22"
    private const val baseParams = "?apiKey=$apiKey"
    private const val DEFAULT_PAGE_SIZE = 10

    fun concatUrl(query: String, pageSize: Int = DEFAULT_PAGE_SIZE, page: Int): String {
        val customParameters = "&q=$query&pageSize=$pageSize&page=$page"
        return baseUrl + baseParams + customParameters
    }
}