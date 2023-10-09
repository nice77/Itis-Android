package com.example.task

import android.content.res.Configuration
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.task.base.BaseActivity
import com.example.task.databinding.ActivityMainBinding
import com.example.task.fragments.ScreenFourFragment
import com.example.task.fragments.ScreenOneFragment

class MainActivity : BaseActivity() {

    override val fragmentContainerId: Int = R.id.fragment_container
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {

            binding?.apply {
                fragmentContainerSpec.isVisible = true
            }
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    ScreenOneFragment.getInstance(),
                    ScreenOneFragment.SCREEN_ONE_FRAGMENT_TAG
                )
                .replace(
                    R.id.fragment_container_spec,
                    ScreenFourFragment.getInstance(),
                    ScreenFourFragment.SCREEN_FOUR_FRAGMENT_TAG
                )
                .commit()
        }
        else {
            binding?.apply {
                fragmentContainerSpec.isVisible = false
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container,
                    ScreenOneFragment.getInstance(),
                    ScreenOneFragment.SCREEN_ONE_FRAGMENT_TAG)
                .commit()
        }
    }
}