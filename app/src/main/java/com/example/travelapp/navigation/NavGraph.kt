package com.example.travelapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.travelapp.ui.screens.HomeScreen
import com.example.travelapp.ui.screens.DetailsScreen
import com.example.travelapp.viewModel.DestinationsViewModel

@Composable
fun AppNavGraph(navController: NavHostController, viewModel: DestinationsViewModel) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(navController = navController, viewModel = viewModel)
        }
        composable("details/{destinationId}") { backStackEntry ->
            val destinationId = backStackEntry.arguments?.getString("destinationId")?.toInt()
            DetailsScreen(destinationId = destinationId, viewModel = viewModel)
        }
    }
}

