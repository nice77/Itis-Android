package com.example.task.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.task.R
import com.example.task.databinding.FragmentCoroutinesBinding

class CoroutinesFragment : Fragment(R.layout.fragment_coroutines) {

    private var binding : FragmentCoroutinesBinding ?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoroutinesBinding.inflate(inflater)
        return binding!!.root
    }

}