package com.example.task.utils

object Service {
    fun checkSize(size : Int) : Boolean = size < 1 || 12 < size

    fun checkByRegexp(text : String?) : Boolean = !text!!.matches(Regex("\\+7 \\(9.{2}\\)-.{3}-.{2}-.{2}"))
}