package com.example.kinematics.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kinematics.models.TestViewModel
import com.example.kinematics.repository.TopicsRepository
import com.example.kinematics.ui.screens.HomeScreen
import com.example.kinematics.ui.screens.TestResultScreen
import com.example.kinematics.ui.screens.TestScreen
import com.example.kinematics.ui.screens.TopicScreen

enum class Screens() {
    Home, Topic, Test, TestResult
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KinematicsApp(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            KinematicsTopBar(
                backStackEntry = backStackEntry,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
         },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screens.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screens.Home.name) {
                HomeScreen(
                    onTopicPage = { topicId ->
                        navController.navigate(Screens.Topic.name + "/$topicId")
                    }
                )
            }

            composable(
                route = "${Screens.Topic.name}/{topicId}",
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
                route = "${Screens.Test.name}/{topicId}",
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
                route = "${Screens.TestResult.name}/{topicId}/{score}/{totalScore}",
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KinematicsTopBar(
    backStackEntry: NavBackStackEntry?,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit
) {
    val route = backStackEntry?.destination?.route
    val title = when (route) {
        Screens.Home.name -> "Выберите тему"
        "${Screens.Topic.name}/{topicId}" -> getTitleFromTopicId(backStackEntry, "topicId")
        "${Screens.Test.name}/{topicId}" -> "Тест"
        "${Screens.TestResult.name}/{topicId}/{score}/{totalScore}" -> "Результат"
        else -> ""
    }

    TopAppBar(
        title = {
            Text(
                text = title,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        }
    )
}

@Composable
private fun getTitleFromTopicId(backStackEntry: NavBackStackEntry, argumentKey: String): String {
    val topicId = backStackEntry.arguments?.getString(argumentKey)
    return TopicsRepository.getTopic(topicId.toString())?.title ?: ""
}