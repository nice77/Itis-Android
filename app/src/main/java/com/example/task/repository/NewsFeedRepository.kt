package com.example.task.repository

import com.example.task.data.DateData
import com.example.task.data.NewsData
import com.example.task.utils.NewsFeedResourcesRepository.bodyList
import com.example.task.utils.NewsFeedResourcesRepository.headerList
import com.example.task.utils.NewsFeedResourcesRepository.imageList
import java.util.Calendar

object NewsFeedRepository {

    private var newsBuffered : MutableList<Any?> = mutableListOf()
    private var news : MutableList<Any?> = mutableListOf()
    private var idCounter = 0
    private var dates : MutableList<Int> = mutableListOf()

    fun getNewsList() : List<Any?> {
        return this.news
    }

    fun add(toAdd : Int, random : Boolean) {
        for (i in news.size until news.size + toAdd) {
            news.add(if (random) (2 .. news.size).random() else i,
                NewsData(
                    id = idCounter,
                    header = headerList.random(),
                    body = bodyList.random(),
                    image = imageList.random()
                )
            )
            idCounter++
        }

        if (random) {
            reCalculateDates()
        }
    }

    fun create(listSize : Int) {
        news.clear()
//        println("News after clearing: $news")
        add(listSize, false)
//        println("News after adding NewsItems: $news")
        addButton()
//        println("News after adding button: $news")
        addDates()
        println("News after adding dates: $news")
    }

    fun addButton() {
        news.add(0, null)
    }

    fun addDates() {
        val space = 8
        val datesNum = news.size / space
        var start = 1
        for (i in 0 .. datesNum) {
            if (start < news.size) {
                dates.add(start)
                news.add(start, DateData(date="${Calendar.DAY_OF_MONTH} ${Calendar.MONTH}"))
                start += space + 1
            }
        }
    }

    fun reCalculateDates() {
        dates.clear()
        for (i in 0 until news.size) {
            if (news[i] is DateData) {
                dates.add(i)
            }
        }
    }

    fun replace(pos : Int, newsData: NewsData) {
        this.news[pos] = newsData
    }

    fun removeById(id : Int) {
        this.newsBuffered = this.news.toMutableList()
        this.news.remove(this.news.find { elem -> (elem as? NewsData)?.id == id })
        println("newsBuffered: $newsBuffered")
        reCalculateDates()
    }

    fun restore() {
        news = this.newsBuffered.toMutableList()
        reCalculateDates()
    }

    fun getDatesList() : MutableList<Int> = dates

    fun getSize() : Int = news.size

    override fun toString(): String {
        return "$news\n$dates"
    }
}