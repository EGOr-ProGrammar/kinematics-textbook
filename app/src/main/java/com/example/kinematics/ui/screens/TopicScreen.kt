package com.example.kinematics.ui.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kinematics.R
import com.example.kinematics.models.ContentItem
import com.example.kinematics.repository.TopicsRepository

@Composable
fun TopicScreen(
    onTest: (String) -> Unit,
    topicId: String
) {
    val topic = remember(topicId) { TopicsRepository.getTopic(topicId) }

    topic?.let {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = topic.title,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            items(topic.contentItems ) { item ->
                when (item) {
                    is ContentItem.Theory -> TheoryCard(item.text)
                    is ContentItem.Image -> ImageCard(item.resId, item.caption)
                    is ContentItem.Question -> QuestionCard(item.questions)
                }
            }

            item {
                Button(
                    onClick = { onTest(topicId) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(stringResource(R.string.take_test))
                }
            }
        }
    } ?: run {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Тема не найдена")
        }
    }
}

@Composable
fun TheoryCard(
    @StringRes paragraph: Int
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
        ),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(paragraph),
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        )
    }
}

@Composable
fun ImageCard(
    @DrawableRes img: Int,
    caption: String?
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(img),
                contentDescription = null,
            )
            Text(
                text = caption.toString(),
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}

@Composable
fun QuestionCard(
    @StringRes questions: Int
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xff24b66e)
        ),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = stringResource(R.string.review_questions_title),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = stringResource(questions),
                fontSize = 16.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TopicPagePreview() {
    TopicScreen(
        {},
        topicId = "movement"
    )
}