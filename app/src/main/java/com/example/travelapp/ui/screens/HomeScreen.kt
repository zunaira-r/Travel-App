package com.example.travelapp.ui.screens

// Compose & UI
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
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
        topBar = { TopAppBar(title = { Text("✈\uFE0F Choose Your Next Travel Destination") }) }
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
                    onClick = { navController?.navigate("details/${destination.id}") }
                )
            }
        }
    }
}

@Composable
fun DestinationCard(
    destination: Destination,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = destination.imageRes),
                contentDescription = destination.name,
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(destination.name, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(destination.description, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}


/** Optional preview that uses a fake ViewModel replacement so the preview can show something.
 *  This avoids requiring NavController during preview. */
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    // Small preview list so preview works even without viewModel injection
    val sampleDestinations = listOf(
        Destination(
            1,
            "Japan",
            R.drawable.japan,
            "Known for its cherry blossoms but has so much more. "
        ),
        Destination(2, "Hawaii", R.drawable.hawaii, "Escape to the island breeze!"),
        Destination(3, "France", R.drawable.france, "The ever-famous, city of love.")
    )

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun FakeHome() {
        Scaffold(topBar = { TopAppBar(title = { Text("✈\uFE0FChoose Your next Travel Destination") }) }) { padding ->
            LazyColumn(
                contentPadding = padding,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(sampleDestinations) { destination ->
                    DestinationCard(
                        destination = destination,
                        onClick = {}
                    )
                }
            }
        }
    }

    FakeHome()
}
