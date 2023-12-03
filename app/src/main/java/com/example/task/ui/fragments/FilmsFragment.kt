package com.example.task.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.task.R
import com.example.task.ui.adapters.AdapterFavorites
import com.example.task.ui.adapters.AdapterFilms
import com.example.task.databinding.FragmentFilmsBinding

class FilmsFragment : Fragment(R.layout.fragment_films) {

    private var binding : FragmentFilmsBinding ?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFilmsBinding.inflate(inflater)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("OnViewCreated in FilmsFragment")
        binding = FragmentFilmsBinding.bind(view)

        binding?.let { binding ->
//            binding.favoritesRv.adapter = AdapterFavorites()
//            binding.filmsRv.adapter = AdapterFilms()
        }
    }
}