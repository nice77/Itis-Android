package com.example.task.utils

import android.view.View
import com.example.task.repository.NewsFeedRepository

class UndoListener : View.OnClickListener {
    override fun onClick(p0: View?) {
        NewsFeedRepository.restore()
    }
}
