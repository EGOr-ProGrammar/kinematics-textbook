package com.example.kinematics.ui.models

data class TestQuestion(
    val questionText: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)
