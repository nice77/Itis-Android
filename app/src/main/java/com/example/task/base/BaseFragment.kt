package com.example.task.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes layout: Int) : Fragment(layout) {
}