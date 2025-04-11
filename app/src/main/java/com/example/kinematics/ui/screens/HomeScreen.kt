package com.example.kinematics.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kinematics.repository.TopicsRepository

@Composable
fun HomeScreen(
    onTopicPage: (String) -> Unit,
) {
    val topics = TopicsRepository.topics.toList()
    LazyColumn(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = AbsoluteAlignment.Left
    ) {
        items(topics) { (topicId, topic) ->
            TopicCard(
                title = topic.title,
                description = topic.description,
                onClick = { onTopicPage(topicId) }
            )
        }
    }
}

@Composable
fun TopicCard(
    title: String = "Заголовок темы",
    description: String = "Описание темы",
    onClick: () -> Unit
) {
    Card(
        shape = CardDefaults.outlinedShape,
        modifier = Modifier.padding(8.dp),
        onClick = {
            onClick()
        }
    ) {
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = description,
            fontSize = 16.sp,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(onTopicPage = {})
}

@Preview
@Composable
private fun TopicCardPreview() {
    TopicCard(onClick = {})
}
