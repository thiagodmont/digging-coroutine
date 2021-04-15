package com.digging.coroutine.repository

import com.digging.coroutine.domain.model.NewsModel

interface NewsRepository {
    suspend fun getNews(about: String): List<NewsModel>
}