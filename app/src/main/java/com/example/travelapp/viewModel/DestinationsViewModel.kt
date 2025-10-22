package com.example.travelapp.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.travelapp.Destination
import com.example.travelapp.R

class DestinationsViewModel : ViewModel() {

    val destinations = listOf(
        Destination(
            id = 1,
            name = "Japan",
            imageRes = R.drawable.japan,
            description = "Known for its cherry blossoms but has so much more."
        ),
        Destination(
            id = 2,
            name = "Hawaii",
            imageRes = R.drawable.hawaii,
            description = "Escape to the island breeze!"
        ),
        Destination(
            id = 3,
            name = "France",
            imageRes = R.drawable.france,
            description = "The ever-famous, city of love."
        )
    )

    val favoriteDestinations = mutableStateListOf<Destination>()

    fun toggleFavorite(destination: Destination) {
        if (favoriteDestinations.contains(destination)) {
            favoriteDestinations.remove(destination)
        } else {
            favoriteDestinations.add(destination)
        }
    }
}