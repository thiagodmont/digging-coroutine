package com.digging.coroutine.ui.request

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.viewModelScope
import com.digging.coroutine.base.BaseViewModel
import com.digging.coroutine.base.ViewState
import com.digging.coroutine.domain.model.NewsModel
import com.digging.coroutine.domain.usecase.FetchNewsUseCase
import com.digging.coroutine.extensions.toLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

internal class RequestViewModelImpl(
    private val fetchNewsUseCase: FetchNewsUseCase,
): RequestViewModel() {

    override val newsViewState = MutableLiveData<ViewState>(ViewState.LOADING)
    override val news = MutableLiveData<List<NewsModel>>()

    override fun fetchNews() {
        val params = FetchNewsUseCase.Params(about = "bitcoin")

        // Any coroutine launched in this scope is automatically canceled if the ViewModel is cleared.
        // .launch (Dispatcher.Main.immediate)
        viewModelScope.launch {
            println("viewModelScope.launch: I'm working in thread ${Thread.currentThread().name}") // Main;
            // Update value in Main Thread;
            newsViewState.value = ViewState.LOADING

            val viewState = runCatching {
                println("runCatching: I'm working in thread ${Thread.currentThread().name}") // Main;
                // Fetch in IO and update in Main;
                news.value = fetchNewsUseCase.execute(params) // IO and Main;

                ViewState.LOADED
            }.recoverCatching {
                Timber.e("Failed to fetchNews UseCase")
                ViewState.ERROR
            }.getOrDefault(ViewState.ERROR)

            // Update value in Main Thread;
            println("runCatching: I'm working in thread ${Thread.currentThread().name}") // Main;
            newsViewState.value = viewState
        }
    }
}