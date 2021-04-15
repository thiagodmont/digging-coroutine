package com.digging.coroutine.network.model

import com.digging.coroutine.domain.model.NewsModel
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class NewsResponse(
    val source: SourceResponse? = null,
    val author: String? = null,
    val title: String? = null,
    val description: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val publishedAt: String? = null,
    val content: String? = null
)

internal fun NewsResponse.toDomain(): NewsModel {
    return NewsModel(
        source = source?.toDomain(),
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content
    )
}