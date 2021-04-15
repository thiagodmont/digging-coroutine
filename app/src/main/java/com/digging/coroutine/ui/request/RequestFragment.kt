package com.digging.coroutine.ui.request

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DefaultItemAnimator
import com.digging.coroutine.R
import com.digging.coroutine.base.BaseFragment
import com.digging.coroutine.base.ViewState
import com.digging.coroutine.base.viewBinding
import com.digging.coroutine.databinding.FragmentRequestBinding
import com.digging.coroutine.extensions.observe
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class RequestFragment : BaseFragment(R.layout.fragment_request) {
    private val binding by viewBinding(FragmentRequestBinding::bind)
    private val viewModel: RequestViewModel by viewModel()
    private val adapter: NewsAdapter by inject()

    override fun setupView(savedInstanceState: Bundle?) {
        super.setupView(savedInstanceState)
        configureRecyclerView()
        viewModel.fetchNews()
    }

    override fun setupObservers() {

        observe(viewModel.news) {
            adapter.data = it
        }

        observe(viewModel.newsViewState) {
            when (it) {
                ViewState.LOADING -> {
                    binding.clpLoading.isVisible = true
                }
                ViewState.ERROR -> {
                    binding.clpLoading.isVisible = false
                    binding.mtxTitleErrorState.isVisible = true
                }
                ViewState.LOADED -> {
                    binding.clpLoading.isVisible = false
                    binding.rvNews.isVisible = true
                }
                ViewState.EMPTY -> {
                    binding.mtxTitleEmptyState.isVisible = true
                    binding.clpLoading.isVisible = false
                }
            }
        }
    }

    private fun configureRecyclerView() {
        binding.rvNews.apply {
            itemAnimator = DefaultItemAnimator()
            adapter = this@RequestFragment.adapter
        }
    }
}