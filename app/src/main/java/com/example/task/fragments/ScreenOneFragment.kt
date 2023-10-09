package com.example.task.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.task.R
import com.example.task.base.BaseFragment
import com.example.task.databinding.FragmentScreenOneBinding
import com.example.task.service.Service

class ScreenOneFragment : BaseFragment(R.layout.fragment_screen_one) {

    private var binding: FragmentScreenOneBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScreenOneBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply{
            submitBtn.setOnClickListener {
                val message = binding.let {
                    inputEt.text.toString()
                }
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container,
                        if (message.isNotEmpty()) {
                            ScreenTwoFragment.getInstance(
                                toMove=true,
                                message=binding?.inputEt?.text.toString()
                            )
                        }
                        else {
                            ScreenTwoFragment.getInstance(
                                toMove=true
                            )
                         },
                        ScreenTwoFragment.SCREEN_TWO_FRAGMENT)
                    .addToBackStack(null)
                    .commit()
            }
            saveBtn.setOnClickListener {
                Service.addValue(inputEt.text.toString())
                inputEt.text = null
                val sfc : ScreenFourFragment = parentFragmentManager.findFragmentByTag(ScreenFourFragment.SCREEN_FOUR_FRAGMENT_TAG) as ScreenFourFragment
                sfc.rewrite()
            }
        }
    }

    companion object {
        const val SCREEN_ONE_FRAGMENT_TAG = "SCREEN_ONE_FRAGMENT_TAG"
        fun getInstance(): ScreenOneFragment {
            return ScreenOneFragment()
        }
    }
}