package com.example.task.utils

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Deferred

object CoroutinesSettings {
    var isStoppedOnBackground = false
    var isAsync = false
    var jobsList = mutableListOf<Deferred<Int>>()
    var isCollapsed = false

    fun cancelAll() {
        jobsList.forEach {job ->
            println("Cancelling: $job")
            job.cancel()
        }
        outputAll()
        jobsList.clear()
    }

    fun outputAll() {
        print("Job is done: ")
        println(jobsList.forEach {item -> print("${item.getCompleted()}, ") })
    }
}