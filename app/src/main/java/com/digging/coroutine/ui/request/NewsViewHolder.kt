package com.digging.coroutine.ui.request

import com.digging.coroutine.base.BaseViewHolder
import com.digging.coroutine.databinding.ItemNewsBinding
import com.digging.coroutine.domain.model.NewsModel

internal class NewsViewHolder(private val viewBinding: ItemNewsBinding):
    BaseViewHolder(viewBinding) {

    fun binding(news: NewsModel) {
        viewBinding.mtvTitleNews.text = news.title
        viewBinding.mtvContentNews.text = news.content
    }
}