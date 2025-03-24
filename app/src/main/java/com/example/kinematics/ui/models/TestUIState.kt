package com.example.kinematics.ui.models

data class TestUIState(
    val questions: List<TestQuestion> = emptyList<TestQuestion>(),
    var currentQuestionIndex: Int = 0,
    var currentQuestion: TestQuestion = questions[currentQuestionIndex]
)
