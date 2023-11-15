package com.example.paginationchallenge.ui.navigation

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.paginationchallenge.core.navigation.routes.CharactersGraph
import com.example.paginationchallenge.core.utils.Constants.CHARACTER_GRAPH
import com.example.paginationchallenge.ui.mainscreen.CharactersScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = CharactersGraph.CharactersList.route,
    ) {
        composable(CharactersGraph.CharactersList.route) {
            CharactersScreen(
                onClick = {
                    navController.navigate(CharactersGraph.CharactersDetail.route + "/$it")
                }
            )
        }

        composable(
            CharactersGraph.CharactersDetail.route + "/{characterId}",
            arguments = listOf(navArgument("characterId") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("characterId")
            Log.e("characterId", id.toString())
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = id.toString())
            }
        }
    }
}
