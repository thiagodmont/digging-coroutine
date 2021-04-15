package com.digging.coroutine.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import kotlinx.android.extensions.LayoutContainer

internal open class BaseViewHolder(binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root), LayoutContainer {

    override val containerView: View = binding.root
}