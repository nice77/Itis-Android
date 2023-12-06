package com.example.task.domain

object CurrentUser {
    private var currentUserId : Int = -1
    fun set(id : Int) {
        currentUserId = id
    }
    fun get() : Int {
        return currentUserId
    }
    fun reset() {
        currentUserId = -1
    }
}