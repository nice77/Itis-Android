package com.example.task.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.task.R
import com.example.task.databinding.FragmentMainPageBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainPageFragment : Fragment(R.layout.fragment_main_page) {

    private var binding : FragmentMainPageBinding ?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainPageBinding.bind(view)

        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.fragment_container_frg_nav_host) as NavHostFragment
        val bottomNavigationView = binding?.bottomNav
        NavigationUI.setupWithNavController(bottomNavigationView!!, navHostFragment.navController)

    }
}