package com.example.task.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.task.R
import com.example.task.base.BaseFragment
import com.example.task.databinding.FragmentScreenFourBinding
import com.example.task.service.Service

class ScreenFourFragment : BaseFragment(R.layout.fragment_screen_four) {

    private var binding: FragmentScreenFourBinding? = null

    fun getBinding() : FragmentScreenFourBinding? {
        return binding
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScreenFourBinding.inflate(inflater, container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentScreenFourBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }

    fun rewrite() {
        binding?.apply {
            unoTv.text = Service.getValue(0) ?: ""
            dosTv.text = Service.getValue(1) ?: ""
            tresTv.text = Service.getValue(2) ?: ""
        }
    }

    companion object {
        const val SCREEN_FOUR_FRAGMENT_TAG = "SCREEN_FOUR_FRAGMENT_TAG"

        fun getInstance() : ScreenFourFragment {
            return ScreenFourFragment()
        }
    }
}