package com.example.task.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.task.fragments.ItemFragment
import java.util.Arrays

class ViewPagerAdapter (
    manager: FragmentManager,
    lifecycle: Lifecycle,
    private val listSize : Int,
    private val answers : Array<Int?>
) : FragmentStateAdapter(manager, lifecycle) {
    override fun createFragment(position: Int): Fragment {
        return ItemFragment(
            pos=position,
            answers=answers
        )
    }

    override fun getItemCount(): Int {
        return listSize
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}