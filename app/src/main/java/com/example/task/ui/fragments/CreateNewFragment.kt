package com.example.task.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.task.R
import com.example.task.data.entities.Film
import com.example.task.data.repository.FilmRepository
import com.example.task.databinding.FragmentCreateNewBinding
import kotlinx.coroutines.launch

class CreateNewFragment : Fragment(R.layout.fragment_create_new) {

    private var binding : FragmentCreateNewBinding ?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCreateNewBinding.bind(view)
        binding?.let { binding ->
            binding.submitBtn.setOnClickListener {
                val name : String = binding.nameEt.text.toString()
                val year : String = binding.yearEt.text.toString()
                val description : String = binding.descriptionEt.text.toString()
                if (name.isEmpty() || year.isEmpty() || description.isEmpty()) {
                    return@setOnClickListener
                }
                val film = Film(
                    name=name,
                    year=Integer.parseInt(year),
                    description=description
                )
                lifecycleScope.launch {
                    val filmRepository = FilmRepository()
                    filmRepository.add(film)
                }
            }
        }
    }
}