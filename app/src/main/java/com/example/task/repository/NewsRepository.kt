package com.example.task.repository

import com.example.task.data.NewsData

object NewsRepository {

    private val news : MutableList<NewsData> = mutableListOf()

    init {
        val list : List<NewsData> = listOf(

            NewsData(
                id = 1,
                header = "Новый рекорд на Олимпийских играх: пловец побил свой личный рекорд",
                body = "На Олимпийских играх проходит ряд ярких событий. Вчера пловец Иван Петров побил свой личный рекорд на дистанции 100 метров баттерфляем. Его результат составил 52,45 секунды, что на 0,2 секунды лучше предыдущего показателя."
            ),

            NewsData(
                id = 2,
                header = "Вулкан на острове проснулся после многолетней спячки",
                body = "На острове Зеленый вулкан, который был в спячке более 50 лет, вновь проявил активность. Из кратера поднимается столб пепла, а извержения сопровождаются громкими звуками и осадками. Власти рекомендуют местным жителям и туристам оставаться на безопасном расстоянии от вулкана."
            ),

        NewsData(
            id = 3,
            header = "Ученые нашли новый метод лечения рака",
            body = "Международная группа ученых сообщила о новом перспективном методе лечения рака. Проведенные на лабораторных мышах эксперименты показали, что новые препараты успешно уничтожают раковые клетки без негативного воздействия на здоровые ткани. В ближайшее время планируется начать клинические испытания."
        ),

        NewsData(
            id = 4,
            header = "В музее открылась выставка редких артефактов древней цивилизации",
            body = "В одном из крупных музеев страны открылась уникальная выставка, где посетители смогут увидеть редкие артефакты древней цивилизации, которая существовала более 2000 лет назад. Среди экспонатов - украшения, оружие и предметы быта, рассказывающие о жизни и культуре этого народа."
        ),

        NewsData(
            id = 5,
            header = "Научные исследования подтвердили пользу сна на открытом воздухе",
            body = "Недавнее исследование, проведенное учеными, показало, что сон на открытом воздухе оказывает положительное влияние на здоровье человека. Уже после нескольких ночей проведенных на природе, испытуемые отметили улучшение сна, повышение энергии и общего самочувствия."
        ),

        )

        news.addAll(list)
    }

    fun getNewsList() : List<NewsData> {
        return this.news
    }
}