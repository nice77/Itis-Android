package com.example.task.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.example.task.R
import com.example.task.base.BaseFragment
import com.example.task.databinding.FragmentScreenThreeBinding

class ScreenThreeFragment : BaseFragment(R.layout.fragment_screen_three) {

    private var binding: FragmentScreenThreeBinding ?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentScreenThreeBinding.bind(view)
        binding?.mainTv?.text = arguments?.getString("message")
    }

    companion object {
        const val SCREEN_THREE_FRAGMENT = "SCREEN_THREE_FRAGMENT"

        fun getInstance(message : String) : ScreenThreeFragment {
            return ScreenThreeFragment().apply {
                arguments = bundleOf("message" to message)
            }
        }
    }
}