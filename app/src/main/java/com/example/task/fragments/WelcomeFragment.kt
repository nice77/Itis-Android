package com.example.task.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.task.R
import com.example.task.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment(R.layout.fragment_welcome) {

    private var binding : FragmentWelcomeBinding ?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomeBinding.inflate(layoutInflater)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWelcomeBinding.bind(view)

        binding?.let {
            it.submitBtn.setOnClickListener { _ ->
                if (it.inputEt.text.isEmpty() ||
                    Integer.parseInt(it.inputEt.text.toString()) <= 0 ||
                    Integer.parseInt(it.inputEt.text.toString()) > 45) {
                    it.inputEt.error = getString(R.string.input_et_error)
                }
                else {
                    parentFragmentManager.beginTransaction()
                        .replace(
                            R.id.fragment_container,
                            NewsListFragment.getInstance(Integer.parseInt(it.inputEt.text.toString())),
                            NewsListFragment.FRAGMENT_NEWS_LIST_TAG
                        )
                        .addToBackStack(null)
                        .commit()
                }
            }
        }
    }

    companion object {

        const val FRAGMENT_WELCOME_TAG = "FRAGMENT_WELCOME_TAG"

        fun getInstance() : WelcomeFragment {
            return WelcomeFragment()
        }
    }
}