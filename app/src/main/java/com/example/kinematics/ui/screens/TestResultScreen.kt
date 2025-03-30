package com.example.kinematics.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import com.example.kinematics.repository.TopicsRepository
import java.lang.ProcessBuilder.Redirect.to

@Composable
fun TestResultScreen(
    backStackEntry: NavBackStackEntry,
    onHome: () -> Unit
) {
    val topicId = backStackEntry.arguments?.getString("topicId") ?: ""
    val scoredPoints = backStackEntry.arguments?.getInt("score") ?: -1
    val totalScore = backStackEntry.arguments?.getInt("totalScore") ?: -1

    val testTitle = TopicsRepository.getTopic(topicId)?.title ?: ""

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Вы набрали $scoredPoints из $totalScore теста по теме \"$testTitle\"",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Button(
            onClick = onHome
        ) {
            Text(text = "В начало")
        }
    }
}