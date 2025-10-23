package com.example.travelapp.ui.screens

// Compose & UI
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

// ViewModel helper
import androidx.lifecycle.viewmodel.compose.viewModel

// Navigation
import androidx.navigation.NavController

// For Previews (optional)
import androidx.compose.ui.tooling.preview.Preview
import com.example.travelapp.Destination
import com.example.travelapp.R
import com.example.travelapp.viewModel.DestinationsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController? = null,
    viewModel: DestinationsViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("✈️ Choose Your Next Travel Destination") },
                actions = {
                    // ❤️ Button to go to Favorites screen
                    IconButton(onClick = { navController?.navigate("favorites") }) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Go to Favorites",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(viewModel.destinations) { destination ->
                DestinationCard(
                    destination = destination,
                    viewModel = viewModel,
                    onClick = { navController?.navigate("details/${destination.id}") }
                )
            }
        }
    }
}

@Composable
fun DestinationCard(
    destination: Destination,
    viewModel: DestinationsViewModel,
    onClick: () -> Unit
) {
    // Checks if destination is already favorited
    val isFavorite = viewModel.favoriteDestinations.contains(destination)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(modifier = Modifier.weight(1f)) {
                Image(
                    painter = painterResource(id = destination.imageRes),
                    contentDescription = destination.name,
                    modifier = Modifier.size(100.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(destination.name, style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(destination.description, style = MaterialTheme.typography.bodyMedium)
                }
            }

            // ❤️ Heart toggle button
            IconButton(onClick = { viewModel.toggleFavorite(destination) }) {
                Icon(
                    imageVector = if (isFavorite)
                        Icons.Filled.Favorite
                    else
                        Icons.Filled.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = if (isFavorite)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val sampleDestinations = listOf(
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

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("✈️ Choose Your Next Travel Destination") },
                actions = {
                    IconButton(onClick = { /* no-op */ }) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Go to Favorites"
                        )
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(sampleDestinations) { destination ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(destination.name)
                        Icon(
                            imageVector = Icons.Filled.FavoriteBorder,
                            contentDescription = "Favorite"
                        )
                    }
                }
            }
        }
    }
}
