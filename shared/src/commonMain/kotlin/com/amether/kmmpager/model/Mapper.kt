package com.amether.kmmpager.model

internal fun ArticleDto.toArticle(): Article {
    return Article(
        source = this.source?.toSource(),
        title = title,
        url = url,
        description = description,
        author = author,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content
    )
}

private fun SourceDto.toSource(): Source {
    return Source(id = id, name = name)
}