package com.digging.coroutine.ui.multi_process

import android.os.Bundle
import androidx.core.view.isVisible
import com.digging.coroutine.R
import com.digging.coroutine.base.BaseFragment
import com.digging.coroutine.base.viewBinding
import com.digging.coroutine.databinding.FragmentMultiProcessBinding
import com.digging.coroutine.extensions.observe
import org.koin.androidx.viewmodel.ext.android.viewModel

class MultiProcessFragment: BaseFragment(R.layout.fragment_multi_process) {
    private val viewBind by viewBinding(FragmentMultiProcessBinding::bind)
    private val viewModel: MultiProcessViewModel by viewModel()

    override fun setupView(savedInstanceState: Bundle?) {
        super.setupView(savedInstanceState)

        viewBind.mbStartProcess.setOnClickListener {
            viewModel.startMultiProcess()
        }

        viewBind.mbCancelJobOne.setOnClickListener {
            viewModel.cancelJobOne()
        }

        viewBind.mbCancelJobTwo.setOnClickListener {
            viewModel.cancelJobTwo()
        }

        viewBind.mbCancelAllScope.setOnClickListener {
            viewModel.cancelAllScope()
        }
    }

    override fun setupObservers() {
        observe(viewModel.loadingJobOne) { loading ->
            viewBind.clpLoadingJobOne.isVisible = loading
            viewBind.mtvTitleLoadingJobOne.isVisible = loading
            viewBind.mbCancelJobOne.isVisible = loading

            if (loading) {
                viewBind.mbCancelAllScope.isVisible = true
            } else if (loading == false && viewModel.loadingJobTwo.value == false) {
                viewBind.mbCancelAllScope.isVisible = false
            }
        }

        observe(viewModel.loadingJobTwo) { loading ->
            viewBind.clpLoadingJobTwo.isVisible = loading
            viewBind.mtvTitleLoadingJobTwo.isVisible = loading
            viewBind.mbCancelJobTwo.isVisible = loading

            if (loading) {
                viewBind.mbCancelAllScope.isVisible = true
            } else if (loading == false && viewModel.loadingJobOne.value == false) {
                viewBind.mbCancelAllScope.isVisible = false
            }
        }
    }
}