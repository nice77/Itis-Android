package com.example.task.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.task.R
import com.example.task.data.entities.User
import com.example.task.data.repository.UserRepository
import com.example.task.databinding.FragmentProfileBinding
import com.example.task.domain.CurrentUser
import kotlinx.coroutines.launch

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var binding : FragmentProfileBinding ?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

        binding?.let { binding ->
            lifecycleScope.launch {
                val userRepository = UserRepository()
                val currentUser = userRepository.getUserById(CurrentUser.get())
                binding.nameTv.text = currentUser.name
                binding.emailTv.text = currentUser.email
                binding.phoneTv.text = currentUser.phone

                binding.settingsNameTv.setText(currentUser.name)
                binding.settingsEmailTv.setText(currentUser.email)
                binding.settingsPhoneTv.setText(currentUser.phone)
            }

            binding.submitBtn.setOnClickListener {
                lifecycleScope.launch {
                    val userRepository = UserRepository()
                    val currentUser = userRepository.getUserById(CurrentUser.get())

                    val name = binding.settingsNameTv.text.toString()
                    val email = binding.settingsEmailTv.text.toString()
                    val phone = binding.settingsPhoneTv.text.toString()
                    val password = binding.settingsPasswordTv.text.toString()
                    val user = currentUser.copy(
                        name=name,
                        email=email,
                        phone=phone,
                        password= password.ifEmpty { currentUser.password }
                    )
                    userRepository.updateUser(user)
                    if (password != currentUser.password && password.isNotEmpty()) {
                        navigateToAuth()
                    }
                }
            }
            binding.exitBtn.setOnClickListener {
                CurrentUser.reset()
                navigateToAuth()
            }
        }
    }

    fun navigateToAuth() {
        val navController = requireParentFragment().requireParentFragment().findNavController()
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.mainPageFragment, true)
            .build()
        navController.navigate(R.id.authFragment, null, navOptions)
    }
}