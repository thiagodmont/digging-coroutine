package com.digging.coroutine.ui.request

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.digging.coroutine.R
import com.digging.coroutine.base.BaseViewHolder
import com.digging.coroutine.databinding.ItemNewsBinding
import com.digging.coroutine.domain.model.NewsModel

internal class NewsAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    var data: List<NewsModel> = emptyList()
        set(value) {
            val result = DiffUtil.calculateDiff(NewsDiffCallback(field, value))
            field = value
            result.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            R.layout.item_news -> {
                NewsViewHolder(
                    ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
            else -> throw IllegalArgumentException("Unexpected viewType received: $viewType.")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is NewsViewHolder -> {
                holder.binding(data[position])
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int = R.layout.item_news

    private class NewsDiffCallback(
        private val oldData: List<NewsModel>,
        private val newData: List<NewsModel>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldData.size

        override fun getNewListSize(): Int = newData.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldData[oldItemPosition].title == newData[newItemPosition].title
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldData[oldItemPosition] == newData[newItemPosition]
        }

    }
}