package com.digging.coroutine.ui.request

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.digging.coroutine.base.ViewState
import com.digging.coroutine.domain.model.NewsModel

abstract class RequestViewModel : ViewModel() {
    abstract val news: LiveData<List<NewsModel>>
    abstract val newsViewState: LiveData<ViewState>
    abstract fun fetchNews()
}