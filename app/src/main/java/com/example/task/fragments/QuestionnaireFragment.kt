package com.example.task.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.task.R
import com.example.task.adapter.ViewPagerAdapter
import com.example.task.databinding.FragmentQuestionnaireBinding
import kotlin.math.max

class QuestionnaireFragment : Fragment(R.layout.fragment_questionnaire) {

    private var binding : FragmentQuestionnaireBinding ?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuestionnaireBinding.inflate(inflater, container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentQuestionnaireBinding.bind(view)

        binding?.let {
            val size = arguments?.let { args -> args.getInt("size") }
            var answers : Array<Int?> = arrayOfNulls(size!!)

            it.fragmentVp.adapter = ViewPagerAdapter(parentFragmentManager, lifecycle, size, answers)

            it.fragmentVp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

                var currentState : Int = 0
                var currentPosition : Int = 0

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    if (currentState == ViewPager2.SCROLL_STATE_DRAGGING && positionOffsetPixels == 0) {
                       if (currentPosition == 0)  {
                           it.fragmentVp.currentItem = size - 1
                       }
                        else if (currentPosition == size - 1) {
                            it.fragmentVp.currentItem = 0
                        }
                    }
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                }

                override fun onPageSelected(position: Int) {
                    currentPosition = position
                    super.onPageSelected(position)
                }

                override fun onPageScrollStateChanged(state: Int) {
                    currentState = state
                    super.onPageScrollStateChanged(state)
                }
            })
        }
    }

    companion object {
        const val QUESTIONNAIRE_FRAGMENT_TAG = "QUESTIONNAIRE_FRAGMENT_TAG"
        fun getInstance() : QuestionnaireFragment {
            return QuestionnaireFragment()
        }
    }
}