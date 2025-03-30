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
        )
    )

    fun getTopic(topicId: String) = topics[topicId]
}