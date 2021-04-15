package com.digging.coroutine.di

import com.digging.coroutine.domain.usecase.FetchNewsUseCase
import com.digging.coroutine.network.NewsNetwork
import com.digging.coroutine.repository.NewsRepository
import com.digging.coroutine.repository.NewsRepositoryImpl
import com.digging.coroutine.ui.multi_process.MultiProcessViewModel
import com.digging.coroutine.ui.process.ProcessViewModel
import com.digging.coroutine.ui.request.RequestViewModelImpl
import com.digging.coroutine.ui.request.NewsAdapter
import com.digging.coroutine.ui.request.RequestViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val viewModule = module {
    single<NewsNetwork> {
        get<Retrofit>(qualifier = ApiNewsQualifier).create(NewsNetwork::class.java)
    }

    single<NewsRepository> { NewsRepositoryImpl(network = get()) }

    factory { FetchNewsUseCase(newsRepository = get()) }
    factory { NewsAdapter() }

    viewModel<RequestViewModel> { RequestViewModelImpl(fetchNewsUseCase = get()) }
    viewModel { ProcessViewModel() }
    viewModel { MultiProcessViewModel() }
}