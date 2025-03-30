package com.example.kinematics.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kinematics.models.TestViewModel
import com.example.kinematics.repository.TopicsRepository
import com.example.kinematics.ui.screens.HomeScreen
import com.example.kinematics.ui.screens.TestResultScreen
import com.example.kinematics.ui.screens.TestScreen
import com.example.kinematics.ui.screens.TopicScreen

enum class Screens(val screen: String) {
    Home("home"), TopicPage("topic"), Test("test"), TestResult("result")
}


@Composable
fun KinematicsApp(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screens.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screens.Home.name) {
                HomeScreen(
                    onTopicPage = { topicId ->
                        navController.navigate(Screens.TopicPage.name + "/$topicId")
                    }
                )
            }

            composable(
                route = Screens.TopicPage.name+"/{topicId}",
                arguments = listOf(navArgument("topicId") { type = NavType.StringType })
            ) { backStackEntry ->
                val topicId = backStackEntry.arguments?.getString("topicId") ?: ""
                TopicScreen(
                    onTest = { topicId ->
                        navController.navigate(Screens.Test.name+"/$topicId")
                    },
                    topicId = topicId
                )
            }

            composable(
                route = Screens.Test.name+"/{topicId}",
                arguments = listOf(navArgument("topicId") { type = NavType.StringType })
            ) { backStackEntry ->
                val topicId = backStackEntry.arguments?.getString("topicId") ?: ""
                val questions = TopicsRepository.getTopic(topicId)?.testQuestions ?: emptyList()

                TestScreen(
                    viewModel = TestViewModel(questions),
                    onResult = { score, totalScore ->
                        navController.navigate(Screens.TestResult.name + "/$topicId/$score/$totalScore")
                    }
                )
            }

            composable(
                route = Screens.TestResult.name+"/{topicId}/{score}/{totalScore}",
                arguments = listOf(
                    navArgument("topicId") { type = NavType.StringType },
                    navArgument("score") { type = NavType.IntType },
                    navArgument("totalScore") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                TestResultScreen(
                    backStackEntry = backStackEntry,
                    onHome = {
                        navController.navigate(Screens.Home.name)
                    }
                )
            }
        }
    }
}