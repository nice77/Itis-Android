package com.example.task.fragments

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.task.R
import com.example.task.databinding.FragmentMainBinding
import com.example.task.utils.NotificationHandler
import com.example.task.utils.NotificationSettings

class MainFragment : Fragment(R.layout.fragment_main) {

    private var binding : FragmentMainBinding ?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        binding?.let { binding ->
            binding.submitBtn.setOnClickListener {
                NotificationHandler.createNotification(
                    requireContext(),
                    binding.headerEt.text.toString(),
                    binding.bodyEt.text.toString()
                )
            }
        }
    }
}