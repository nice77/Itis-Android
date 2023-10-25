package com.example.task.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.task.R
import com.example.task.databinding.ItemFragmentBinding
import com.example.task.questions.Questions

class ItemFragment(
    val pos : Int,
    val answers : Array<Int?>
) : Fragment(R.layout.item_fragment) {

    private var binding : ItemFragmentBinding ?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ItemFragmentBinding.inflate(inflater, container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ItemFragmentBinding.bind(view)
        binding?.let {
            val question = Questions.getQuestions()[pos]
            it.centerTv.text = (pos + 1).toString() + ") " + question.question
            it.questionOneRbtn.text = question.optionOne
            it.questionTwoRbtn.text = question.optionTwo
            it.questionThreeRbtn.text = question.optionThree
            it.questionFourRbtn.text = question.optionFour
            it.questionFiveRbtn.text = question.optionFive
            it.endBtn.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container,
                        WelcomeFragment.getInstance().apply {
                            arguments = bundleOf("ended" to true)
                        },
                        WelcomeFragment.WELCOME_FRAGMENT_TAG)
                    .commit()
            }
            it.radiogroup.setOnCheckedChangeListener { radioGroup, i ->
                if (answers[pos] != null && radioGroup.checkedRadioButtonId == answers[pos]) {
                    view.findViewById<RadioButton>(radioGroup.checkedRadioButtonId).isChecked = true
                }
                answers[pos] = i
                if (!answers.contains(null) && pos == answers.size - 1) {
                    it.endBtn.visibility = View.VISIBLE
                }
            }
        }
    }
    override fun onResume() {
        binding?.let {
            if (!answers.contains(null) && pos == answers.size - 1) {
                it.endBtn.visibility = View.VISIBLE
            }
        }
        super.onResume()
    }
}