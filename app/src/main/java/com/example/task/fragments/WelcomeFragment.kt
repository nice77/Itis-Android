package com.example.task.fragments

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.task.R
import com.example.task.databinding.FragmentWelcomeBinding
import com.example.task.utils.Service

class WelcomeFragment : Fragment(R.layout.fragment_welcome) {

    private var binding : FragmentWelcomeBinding ?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { args ->
            if (args.getBoolean("ended")) {
                Toast.makeText(context, "Test ended!", Toast.LENGTH_LONG).show()
            }
        }

        binding = FragmentWelcomeBinding.bind(view)

        binding?.let {
            it.submitBtn.setOnClickListener { _ ->
                val size = if (it.questionsNumberEt.text.toString() == "") 0 else Integer.parseInt(it.questionsNumberEt.text.toString())
                if (Service.checkSize(size)) {
                    it.questionsNumberEt.error = "Questions number must be between 1 and 12!"
                }
                if (Service.checkByRegexp(it.phoneEt.text.toString())) {
                    it.phoneEt.error = "Wrong number format!"
                }
                else if (!Service.checkSize(size) && !Service.checkByRegexp(it.phoneEt.text.toString())) {
                    parentFragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.fragment_container,
                            QuestionnaireFragment.getInstance().apply {
                                val bundle = Bundle().apply {
                                    this.putInt("size", size)
                                }
                                arguments = bundle
                            },
                            QuestionnaireFragment.QUESTIONNAIRE_FRAGMENT_TAG
                        )
                        .commit()
                }
            }

            it.phoneEt.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(input: CharSequence?, start: Int, count: Int, after: Int) {
                    it.phoneEt.removeTextChangedListener(this)
                    if (input?.length == 0) {
                        it.phoneEt.setText("+7 (9")
                    }
                    it.phoneEt.addTextChangedListener(this)
                    it.phoneEt.setSelection(it.phoneEt.text?.length ?: 0)
                }

                override fun onTextChanged(input: CharSequence?, start: Int, before: Int, count: Int) {
                    it.phoneEt.removeTextChangedListener(this)
                    val textEt = it.phoneEt.text.toString()
                    if (before == 1) {}
                    else if (textEt.matches(Regex("\\+7 \\(9.{2}\\)-.{3}-.{2}"))) {
                        it.phoneEt.setText("$input-")
                    }
                    else if (textEt.matches(Regex("\\+7 \\(9.{2}\\)-.{3}"))) {
                        it.phoneEt.setText("$input-")
                    }
                    else if (textEt.matches(Regex("\\+7 \\(9.{2}"))) {
                        it.phoneEt.setText("$input)-")
                    }
                    else if (textEt.matches(Regex("\\+7 \\(9"))) {
                        it.phoneEt.setText(textEt + input)
                    }
                    it.phoneEt.addTextChangedListener(this)
                    it.phoneEt.setSelection(it.phoneEt.text?.length ?: 0)
                }
                override fun afterTextChanged(input: Editable?) {}
            })
        }
    }

    companion object {
        const val WELCOME_FRAGMENT_TAG = "WELCOME_FRAGMENT_TAG"
        fun getInstance() : WelcomeFragment {
            return WelcomeFragment()
        }
    }
}