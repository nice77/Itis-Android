package com.example.task.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.task.R
import com.example.task.data.NewsData
import com.example.task.databinding.FragmentBottomSheetBinding
import com.example.task.repository.NewsFeedRepository
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment(
    private val notify : (oldList : List<Any?>, newList : List<Any?>) -> Unit
) : BottomSheetDialogFragment(R.layout.fragment_bottom_sheet) {

    private var binding : FragmentBottomSheetBinding ?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomSheetBinding.inflate(inflater)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBottomSheetBinding.bind(view)

        binding?.let {binding ->
            binding.dialogSubmitBtn.setOnClickListener {
                val toAddNumber = Integer.parseInt(binding.dialogInputEt.text.toString())
                if (toAddNumber < 0 || 5 < toAddNumber) {
                    binding.dialogInputEt.error = getString(R.string.dialog_input_et_error)
                }
                else {
                    val oldList = NewsFeedRepository.getNewsList().toList()
                    NewsFeedRepository.add(toAddNumber, true)
                    notify(oldList,
                        NewsFeedRepository.getNewsList())
                    dismiss()
                }
                println(NewsFeedRepository.getNewsList())
            }
        }
    }

    companion object {
        const val BOTTOM_SHEET_FRAGMENT_TAG = "BOTTOM_SHEET_FRAGMENT_TAG"

        fun getInstance(
            notify : (oldList : List<Any?>, newList : List<Any?>) -> Unit
        ) : BottomSheetFragment {
            return BottomSheetFragment(notify)
        }
    }
}
