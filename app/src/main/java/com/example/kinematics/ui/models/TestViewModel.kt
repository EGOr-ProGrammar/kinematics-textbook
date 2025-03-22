package com.example.kinematics.ui.models

import androidx.lifecycle.ViewModel
import com.example.kinematics.datasources.Test1DataSource.questions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TestViewModel: ViewModel() {
    private var score = 0

    private val _uiState = MutableStateFlow(TestUIState())
    val uiState = _uiState.asStateFlow()
    private val totalScore = _uiState.value.questions.size


    fun onNextQuestion(selectedOption: Int?) {
        if (selectedOption != null) {
            if (selectedOption == _uiState.value.currentQuestion.correctAnswerIndex) {
                score++
            }
            if (_uiState.value.currentQuestionIndex <  totalScore - 1) {
                _uiState.update { state ->
                    state.copy(
                        currentQuestionIndex = state.currentQuestionIndex+1,
                        currentQuestion = state.questions[state.currentQuestionIndex+1]
                    )
                }
            }
        }
    }
    // TODO: return to previous question with accurate change in score
//    fun onPreviousQuestion() {
//
//    }

    init {

    }
}