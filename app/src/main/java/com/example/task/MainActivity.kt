package com.example.task

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.task.fragments.WelcomeFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        supportFragmentManager.beginTransaction()
            .add(
                R.id.fragment_container,
                WelcomeFragment.getInstance(),
                WelcomeFragment.FRAGMENT_WELCOME_TAG
            )
            .commit()
    }
}