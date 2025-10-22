package com.example.travelapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.travelapp.navigation.AppNavGraph
import com.example.travelapp.ui.theme.TravelAppTheme
import com.example.travelapp.viewModel.DestinationsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TravelAppTheme(darkTheme = true) {
                val navController = rememberNavController()
                val viewModel: DestinationsViewModel = viewModel()
                AppNavGraph(navController = navController, viewModel = viewModel)
            }
        }
    }
}
