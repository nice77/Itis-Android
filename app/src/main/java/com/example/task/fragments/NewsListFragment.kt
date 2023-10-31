package com.example.task.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task.R
import com.example.task.adapter.NewsListAdapter
import com.example.task.databinding.FragmentNewsListBinding

class NewsListFragment : Fragment(R.layout.fragment_news_list) {

    private var binding : FragmentNewsListBinding ?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsListBinding.inflate(layoutInflater)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsListBinding.bind(view)

        binding?.let {binding ->
            arguments?.let {arguments ->
                if (arguments.getInt("displayListSize") <= 12) {
                    binding.newsRc.layoutManager = LinearLayoutManager(context)
                }
                else {
                    binding.newsRc.adapter = NewsListAdapter(arguments.getInt("displayListSize"))
                    binding.newsRc.layoutManager = GridLayoutManager(context, 2)
                }
            }
        }
    }

    companion object {
        const val FRAGMENT_NEWS_LIST_TAG = "FRAGMENT_NEWS_LIST_TAG"

        fun getInstance(displayListSize : Int) : NewsListFragment {
            return NewsListFragment().apply {
                arguments = bundleOf("displayListSize" to displayListSize)
            }
        }
    }
}