package com.example.kinematics.ui.models

import com.example.kinematics.datasources.Test1DataSource

data class TestUIState(
    val questions: List<TestQuestion> = Test1DataSource.questions,
    var currentQuestionIndex: Int = 0,
    var currentQuestion: TestQuestion = questions[currentQuestionIndex]
)
