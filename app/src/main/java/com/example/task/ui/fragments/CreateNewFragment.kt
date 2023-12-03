package com.example.task.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.task.R

class CreateNewFragment : Fragment(R.layout.fragment_create_new) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("OnViewCreated in CreateNewFragment")
    }

}