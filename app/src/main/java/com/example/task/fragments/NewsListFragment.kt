package com.example.task.fragments

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.task.R
import com.example.task.adapter.NewsListAdapter
import com.example.task.adapter.decorators.HorizontalMargin
import com.example.task.adapter.decorators.ItemHoldTouch
import com.example.task.adapter.decorators.ItemHorizontalTouch
import com.example.task.adapter.decorators.VerticalMargin
import com.example.task.data.NewsData
import com.example.task.databinding.FragmentNewsListBinding
import com.example.task.repository.NewsFeedRepository
import com.example.task.utils.UndoListener
import com.google.android.material.snackbar.Snackbar


class NewsListFragment : Fragment(R.layout.fragment_news_list) {

    private var binding : FragmentNewsListBinding ?= null
    private var recreateList : Boolean = true

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
                if (recreateList) {
                    NewsFeedRepository.create(arguments.getInt("displayListSize"))
                    recreateList = !recreateList
                }
                if (arguments.getInt("displayListSize") <= 12) {
                    val callback: ItemTouchHelper.Callback = ItemHorizontalTouch()
                    val touchHelper = ItemTouchHelper(callback)
                    touchHelper.attachToRecyclerView(binding.newsRc)
                    binding.newsRc.layoutManager = LinearLayoutManager(context)
                }
                else {
                    val callback: ItemTouchHelper.Callback = ItemHoldTouch()
                    val touchHelper = ItemTouchHelper(callback)
                    touchHelper.attachToRecyclerView(binding.newsRc)
                    binding.newsRc.layoutManager = StaggeredGridLayoutManager(2, Configuration.ORIENTATION_PORTRAIT)
                }

                binding.newsRc.adapter = NewsListAdapter(
                    onItemClicked = ::onItemClicked,
                    onLikeButtonClicked = ::onLikeButtonClicked,
                    onButtonClicked = ::onButtonClicked,
                    onItemSwiped = ::onItemSwiped,
                    onItemDeleted = ::onItemDeleted
                )
                val marginValue = 16
                binding.newsRc.addItemDecoration(HorizontalMargin(marginValue))
                binding.newsRc.addItemDecoration(VerticalMargin(marginValue / 2))
            }
        }
    }

    private fun onItemClicked(newsItem : NewsData) {
        parentFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
                NewsItemFragment.getInstance(newsItem),
                NewsItemFragment.NEWS_ITEM_FRAGMENT_TAG
            )
            .addToBackStack(null)
            .commit()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun onButtonClicked() {
        val dialog = BottomSheetFragment.getInstance() {oldList, newList ->
            binding?.let {
                (it.newsRc.adapter as NewsListAdapter).updateItems(oldList, newList)
            }
        }
        dialog.show(childFragmentManager, BottomSheetFragment.BOTTOM_SHEET_FRAGMENT_TAG)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun onLikeButtonClicked(pos : Int, newsItem: NewsData) {
        NewsFeedRepository.replace(pos, newsItem)
        binding?.newsRc?.adapter?.notifyItemChanged(pos, newsItem)
    }

    fun onItemSwiped(itemId : Int) {
        val oldList = NewsFeedRepository.getNewsList().toList()
        NewsFeedRepository.removeById(itemId)
        binding?.let {
            (it.newsRc.adapter as NewsListAdapter).updateItems(oldList, NewsFeedRepository.getNewsList())
        }
    }

    fun onItemDeleted() {
        val sb = Snackbar.make(requireActivity().findViewById(R.id.fragment_container),
            getString(R.string.snackbar_undo),
            Snackbar.LENGTH_SHORT)
        sb.setAction(getString(R.string.snackbar_action)) { _ ->
            val oldList = NewsFeedRepository.getNewsList().toList()
            NewsFeedRepository.restore()
            (binding?.newsRc?.adapter as? NewsListAdapter)?.updateItems(oldList, NewsFeedRepository.getNewsList())
        }
        sb.show()
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
