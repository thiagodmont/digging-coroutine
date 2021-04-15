package com.digging.coroutine.ui.process

import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import com.digging.coroutine.R
import com.digging.coroutine.base.BaseFragment
import com.digging.coroutine.base.viewBinding
import com.digging.coroutine.databinding.FragmentProcessBinding
import com.digging.coroutine.extensions.observe
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProcessFragment : BaseFragment(R.layout.fragment_process) {
    private val viewBind by viewBinding(FragmentProcessBinding::bind)
    private val viewModel: ProcessViewModel by viewModel()

    override fun setupView(savedInstanceState: Bundle?) {
        super.setupView(savedInstanceState)

        viewBind.mbStartProcess.setOnClickListener {
            viewModel.startProcess()
        }

        viewBind.mbCancelProcess.setOnClickListener {
            viewModel.cancelProcess()
        }
    }

    override fun setupObservers() {
        observe(viewModel.loading) { loading ->
            viewBind.clpLoading.isVisible = loading
            viewBind.mbCancelProcess.isVisible = loading
        }

        observe(viewModel.toast) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }
}