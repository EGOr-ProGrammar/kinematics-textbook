package com.example.kinematics.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kinematics.ui.screens.HomeScreen
import com.example.kinematics.ui.screens.TestScreen
import com.example.kinematics.ui.screens.TopicPageScreen

enum class Screens(val index: Int) {
    Home(1), TopicPage(2), Test(3)
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
                    onTopicPage = {
                        navController.navigate(Screens.TopicPage.name)
                    }
                )
            }

            composable(Screens.TopicPage.name) {
                TopicPageScreen(
                    onTest = {
                        navController.navigate(Screens.Test.name)
                    }
                )
            }

            composable(Screens.Test.name) {
                TestScreen()
            }
        }
    }
}