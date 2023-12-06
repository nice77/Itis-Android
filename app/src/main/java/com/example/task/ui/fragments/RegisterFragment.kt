package com.example.task.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.task.App
import com.example.task.R
import com.example.task.data.dao.UserDao
import com.example.task.data.entities.User
import com.example.task.data.repository.UserRepository
import com.example.task.databinding.FragmentRegisterBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private var binding : FragmentRegisterBinding ?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)
        binding?.let { binding ->

            binding.submitBtn.setOnClickListener {
                val name : String = binding.nameEt.text.toString()
                val email : String = binding.emailEt.text.toString()
                val phone : String = binding.phoneEt.text.toString()
                val password : String = binding.passwordEt.text.toString()
                if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                    Snackbar.make(binding.root, getString(R.string.enter_all_fields), Snackbar.LENGTH_LONG).show()
                    return@setOnClickListener
                }
                val user = User(
                    name=name,
                    email=email,
                    phone=phone,
                    password=password
                )

                lifecycleScope.launch {
                    val userRepository = UserRepository()
                    if (!userRepository.addUser(user)) {
                        Snackbar.make(binding.root, getString(R.string.phone_in_use), Snackbar.LENGTH_LONG).show()
                    }
                    else {
                        findNavController().navigate(R.id.action_registerFragment_to_authFragment)
                    }
                }
            }
        }
    }
}