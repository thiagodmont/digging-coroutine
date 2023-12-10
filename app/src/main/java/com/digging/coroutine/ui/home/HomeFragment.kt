package com.digging.coroutine.ui.home

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.digging.coroutine.R
import com.digging.coroutine.base.BaseFragment
import com.digging.coroutine.base.viewBinding
import com.digging.coroutine.databinding.FragmentHomeBinding
import com.digging.coroutine.extensions.click

class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private val viewBind by viewBinding(FragmentHomeBinding::bind)

    override fun setupView(savedInstanceState: Bundle?) {
        super.setupView(savedInstanceState)

        viewBind.mbWithRequest.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToRequestFragment()
            )
        }

        viewBind.mbWithProcess.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToProcessFragment()
            )
        }

        viewBind.mbWithMultiProcess.click {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToMultiProcessFragment()
            )
        }

        viewBind.mbRace.click {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToRaceFragment()
            )
        }
    }
}