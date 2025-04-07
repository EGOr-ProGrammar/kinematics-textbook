package com.example.kinematics.repository

import com.example.kinematics.R
import com.example.kinematics.datasources.TestQuestionsDataSource
import com.example.kinematics.models.ContentItem
import com.example.kinematics.models.TopicData

object TopicsRepository {
    val topics = mapOf(
        "movement" to TopicData(
            title = "Движение",
            description = "Понятие движения является чрезвычайно общим и охватывает самый широкий круг явлений.",
            contentItems = listOf(
                ContentItem.Theory(R.string.topic1_pg1),
                ContentItem.Theory(R.string.topic1_pg2),
                ContentItem.Image(R.drawable.decartes_coordinates, "Пример системы отсчёта"),
                ContentItem.Theory(R.string.topic1_pg3),
                ContentItem.Theory(R.string.topic1_pg4),
                ContentItem.Question(R.string.topic1_questions)
            ),
            testQuestions = TestQuestionsDataSource.questions1
        ),
        "movement_ways" to TopicData(
            title = "Способы описания движения",
            description = "Если тело можно считать точкой, то для описания его движения нужно " +
                    "научиться рассчитывать положение точки в любой момент времени относительно" +
                    " выбранного тела отсчёта.",
            contentItems = listOf(
                ContentItem.Theory(R.string.topic2_pg1),
                ContentItem.Image(
                    R.drawable.kinamatics_equation,
                    caption = "Кинематическое уравнение движения точки в координатной форме."
                ),
                ContentItem.Theory(R.string.topic2_pg2),
                ContentItem.Theory(R.string.topic2_pg3),
                ContentItem.Theory(R.string.topic2_pg4),
                ContentItem.Image(
                    R.drawable.projection,
                    caption = "Проекции точек A, B на ось OX."
                ),
                ContentItem.Question(R.string.topic2_questions)
            ),
            testQuestions = TestQuestionsDataSource.questions2
        )
    )

    fun getTopic(topicId: String) = topics[topicId]
}