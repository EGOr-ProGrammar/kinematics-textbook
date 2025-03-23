package com.example.kinematics.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kinematics.ui.models.TestViewModel


@Composable
fun TestScreen(
    viewModel: TestViewModel = viewModel(),
    onResult: (Int, Int) -> Unit
) {
    var selectedOption by remember { mutableStateOf<Int?>(null) }
    val uiState by viewModel.uiState.collectAsState()

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = uiState.currentQuestion.questionText,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )
        Spacer(Modifier.height(8.dp))

        // TODO: implement TestQuestion composable to increase readability
        uiState.currentQuestion.options.forEachIndexed { index, option ->
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.padding(8.dp)
            ) {
                RadioButton(
                    selected = selectedOption == index,
                    onClick = { selectedOption = index},
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = option,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
        Button(
            onClick = {
                viewModel.onNextQuestion(selectedOption)
                selectedOption = null
                if (uiState.currentQuestionIndex == uiState.questions.size - 1) {
                    onResult(viewModel.score, viewModel.totalScore)
                }
            },

        ) {
            Text(
                text = if (uiState.currentQuestionIndex < uiState.questions.size - 1) "Следующий вопрос"
                else "Итоги"
            )
        }
    }
}

//@Composable
//fun TestQuestion(
//    index: Int,
//    option: String
//) {
//    Row(
//        horizontalArrangement = Arrangement.Start,
//        modifier = Modifier.padding(8.dp)
//    ) {
//        RadioButton(
//            selected = selectedOption == index,
//            onClick = { selectedOption = index},
//            modifier = Modifier.padding(8.dp)
//        )
////        Spacer(Modifier.width(16.dp))
//        Text(
//            text = option,
//            fontSize = 16.sp,
//            modifier = Modifier.padding(start = 8.dp)
//        )
//    }
//}
