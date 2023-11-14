package com.example.paginationchallenge.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.paginationchallenge.ui.mainscreen.MainScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "FirstScreen",
    ) {
        composable("FirstScreen") {
            MainScreen(onClick = {})
        }
    }
}
