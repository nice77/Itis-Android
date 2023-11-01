package com.example.task.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.task.R
import com.example.task.data.NewsData
import com.example.task.databinding.FragmentNewsItemBinding

class NewsItemFragment : Fragment(R.layout.fragment_news_item) {

    private var binding : FragmentNewsItemBinding ?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsItemBinding.inflate(layoutInflater)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsItemBinding.bind(view)

        binding?.let { binding ->
            arguments?.let { args ->
                binding.imageImg.setImageResource(args.getInt("newsImage"))
                binding.headerTv.text = args.getString("newsHeader")
                binding.bodyTv.text = args.getString("newsBody")
            }
        }
    }

    companion object {
        const val NEWS_ITEM_FRAGMENT_TAG = "NEWS_ITEM_FRAGMENT_TAG"
        fun getInstance(newsItem : NewsData) : NewsItemFragment {
            return NewsItemFragment().apply {
                arguments = bundleOf(
                    "newsHeader" to newsItem.header,
                    "newsBody" to newsItem.body,
                    "newsImage" to newsItem.image
                )
            }
        }
    }
}