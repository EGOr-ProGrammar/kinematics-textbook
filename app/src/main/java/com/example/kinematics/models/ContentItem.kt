package com.example.kinematics.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed class ContentItem {
    data class Theory(@StringRes val text: Int) : ContentItem()
    data class Image(@DrawableRes val resId: Int, val caption: String?) : ContentItem()
    data class Question(@StringRes val questions: Int) : ContentItem()
}

data class TopicData(
    val title: String,
    val description: String,
    val contentItems: List<ContentItem>,
    val testQuestions: List<TestQuestion>
)