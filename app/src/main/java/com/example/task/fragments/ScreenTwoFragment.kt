package com.example.task.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.task.R
import com.example.task.base.BaseFragment
import com.example.task.databinding.FragmentScreenOneBinding
import com.example.task.databinding.FragmentScreenTwoBinding

class ScreenTwoFragment : BaseFragment(R.layout.fragment_screen_two) {

    private var binding: FragmentScreenTwoBinding ?= null;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScreenTwoBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentScreenTwoBinding.bind(view)
        arguments?.let {
            if (it.getBoolean("toMove")) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container,
                        ScreenThreeFragment.getInstance(it.getString("message")!!),
                        ScreenThreeFragment.SCREEN_THREE_FRAGMENT)
                    .addToBackStack(null)
                    .commit()
            }
        }

        binding?.apply {
            mainTv.text = arguments?.getString("message")
            submitOneBtn.setOnClickListener {
                parentFragmentManager.popBackStack()
            }

            submitTwoBtn.setOnClickListener {
                parentFragmentManager.popBackStack()
                parentFragmentManager.beginTransaction()
                    .replace(
                        R.id.fragment_container,
                        ScreenThreeFragment.getInstance(arguments?.getString("message")!!),
                        ScreenThreeFragment.SCREEN_THREE_FRAGMENT
                    )
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    override fun onPause() {
        arguments?.let {
            it.remove("toMove")
        }
        super.onPause()
    }

    companion object {
        const val SCREEN_TWO_FRAGMENT = "SCREEN_TWO_FRAGMENT"

        fun getInstance(toMove : Boolean = false, message : String = "No text passed here") : ScreenTwoFragment {
            return ScreenTwoFragment().apply {
                val bundle = Bundle().apply {
                    putBoolean("toMove", toMove)
                    putString("message", message)
                }
                arguments = bundle
            }
        }
    }
}
