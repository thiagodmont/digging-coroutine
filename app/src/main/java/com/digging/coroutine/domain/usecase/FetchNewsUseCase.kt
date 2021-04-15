package com.digging.coroutine.domain.usecase

import com.digging.coroutine.domain.BaseUseCase
import com.digging.coroutine.domain.model.NewsModel
import com.digging.coroutine.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

internal class FetchNewsUseCase(
    private val newsRepository: NewsRepository
) : BaseUseCase<FetchNewsUseCase.Params, List<NewsModel>> {

    data class Params(
        val about: String
    )

    override suspend fun run(params: Params?): List<NewsModel> = try {
        requireNotNull(params) {
            "Failed to load news."
        }

        val (about) = params

        println("useCase withContext: I'm working in thread ${Thread.currentThread().name}")
        newsRepository.getNews(about)
    } catch (e: Exception) {
        throw UseCaseException(e.message, e).also {
            Timber.e(e, "Failed to load news.")
        }
    }

    class UseCaseException @JvmOverloads constructor(
        message: String? = null,
        throwable: Throwable? = null
    ) : Exception(message, throwable)
}