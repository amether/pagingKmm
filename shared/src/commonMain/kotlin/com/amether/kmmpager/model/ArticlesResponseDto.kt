package com.amether.kmmpager.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticlesResponseDto(
    @SerialName("status") val status: String,
    @SerialName("totalResults") val totalResults: Int,
    @SerialName("message") val message: String? = null,
    @SerialName("articles") val articles: List<ArticleDto>,
)