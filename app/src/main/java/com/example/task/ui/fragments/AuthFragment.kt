package com.example.task.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.task.R
import com.example.task.data.repository.UserRepository
import com.example.task.databinding.FragmentAuthBinding
import com.example.task.domain.CurrentUser
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class AuthFragment : Fragment(R.layout.fragment_auth) {

    private var binding : FragmentAuthBinding?= null;

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAuthBinding.bind(view)
        binding?.let { binding ->

            binding.redirectBtn.setOnClickListener {
                findNavController().navigate(R.id.action_authFragment_to_registerFragment)
            }

            binding.submitBtn.setOnClickListener {
                lifecycleScope.launch {
                    val email : String = binding.emailEt.text.toString()
                    val password : String = binding.passwordEt.text.toString()
                    val userRepository = UserRepository()
                    if (userRepository.checkUserCredentials(email, password)) {
                        CurrentUser.set(userRepository.getUserByCredentials(email, password).id)
                        val navController = findNavController()
                        val navOptions = NavOptions.Builder()
                            .setPopUpTo(R.id.authFragment, true)
                            .build()
                        navController.navigate(R.id.mainPageFragment, null, navOptions)
                    }
                    else {
                        Snackbar.make(binding.root, getString(R.string.wrong_credentials), Snackbar.LENGTH_LONG).show()
                    }
                }
            }

        }
    }
}