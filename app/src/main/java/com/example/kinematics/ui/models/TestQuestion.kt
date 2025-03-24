package com.example.kinematics.ui.models

data class TestQuestion(
    val questionText: String = "",
    val options: List<String> = emptyList<String>(),
    val correctAnswerIndex: Int = -1
)
