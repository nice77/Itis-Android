package com.example.task.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.task.R
import com.example.task.data.entities.Film
import com.example.task.data.entities.Rate
import com.example.task.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment(
    private val film : Film,
    private val rate : Rate?,
    private val average : Float,
    private val onButtonClicked : (Int, Int, TextView) -> Unit
) : BottomSheetDialogFragment(R.layout.fragment_bottom_sheet) {

    private var binding : FragmentBottomSheetBinding ?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBottomSheetBinding.bind(view)
        binding?.let { binding ->
            binding.nameTv.text = film.name
            binding.yearTv.text = film.year.toString()
            binding.descriptionTv.text = film.description
            binding.rateTv.text = average.toString()

            if (rate != null) {
                disable(rate.rate)
            }

            binding.submitBtn.setOnClickListener {
                onButtonClicked(film.id, binding.rb.progress, binding.rateTv)
                disable()
            }
        }
    }

    fun disable() {
        binding?.let{ binding ->
            binding.submitBtn.isEnabled = false
            binding.rb.isEnabled = false
        }
    }

    fun disable(progress : Int) {
        binding!!.rb.progress = progress
        disable()
    }

    companion object {
        const val BOTTOM_SHEET_FRAGMENT_TAG = "BOTTOM_SHEET_FRAGMENT_TAG"
        fun getInstance(
            film : Film,
            rate: Rate?,
            average: Float,
            onButtonClicked: (Int, Int, TextView) -> Unit
        ) : BottomSheetFragment {
            return BottomSheetFragment(film, rate, average, onButtonClicked)
        }
    }
}
