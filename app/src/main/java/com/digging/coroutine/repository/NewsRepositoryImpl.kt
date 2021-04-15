package com.digging.coroutine.repository

import com.digging.coroutine.domain.model.NewsModel
import com.digging.coroutine.extensions.parseResponse
import com.digging.coroutine.network.NewsNetwork
import com.digging.coroutine.network.base.NetworkException
import com.digging.coroutine.network.base.Outcome
import com.digging.coroutine.network.model.toDomain

internal class NewsRepositoryImpl(
    private val network: NewsNetwork
) : NewsRepository {

    @Throws(NetworkException::class)
    override suspend fun getNews(about: String): List<NewsModel> {
        return when (val outcome = network.getNews(q = about).parseResponse()) {
            is Outcome.Success -> outcome.value.toDomain()
            is Outcome.Failure -> throw NetworkException.parse(outcome.statusCode)
        }
    }
}