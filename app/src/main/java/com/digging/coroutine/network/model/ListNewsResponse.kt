package com.digging.coroutine.network.model

import com.digging.coroutine.domain.model.NewsModel
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class ListNewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<NewsResponse>
)

internal fun ListNewsResponse.toDomain(): List<NewsModel> {
    return articles.map {
        it.toDomain()
    }
}