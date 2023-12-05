package com.example.task.domain

enum class FilterTypes(
    private val value : Int
) {
    YEAR_ASC(0),
    YEAR_DESC(1),
    RATE_ASC(2),
    RATE_DESC(3);
    fun getValue() = value
}