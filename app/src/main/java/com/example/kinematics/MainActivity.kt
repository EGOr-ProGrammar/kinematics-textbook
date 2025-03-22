package com.example.kinematics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.kinematics.ui.KinematicsApp
import com.example.kinematics.ui.theme.KinematicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KinematicsTheme {
                KinematicsApp()
            }
        }
    }
}
