package com.example.task

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.task.databinding.ActivityMainBinding
import com.example.task.fragments.WelcomeFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private var binding : ActivityMainBinding ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.let {
//            it.scrollerVp2.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container,
                    WelcomeFragment.getInstance(),
                    WelcomeFragment.WELCOME_FRAGMENT_TAG)
                .commit()
        }
    }
}