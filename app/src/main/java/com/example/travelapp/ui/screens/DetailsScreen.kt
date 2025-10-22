package com.example.travelapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.travelapp.viewModel.DestinationsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(destinationId: Int?, viewModel: DestinationsViewModel) {
    val destination = viewModel.destinations.find { it.id == destinationId }
    if (destination == null) {
        Text(text = "Destination not found")
        return
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(destination.name) }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = destination.imageRes),
                contentDescription = destination.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = destination.name,
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            when (destination.name) {
                "Japan" -> {
                    Text(
                        text = """
                            Japan is a country of stunning contrasts, where ancient traditions meet cutting-edge technology. Explore the bustling streets of Tokyo, the serene temples of Kyoto, and the natural beauty of Mount Fuji. Experience world-class cuisine, from sushi to ramen, and immerse yourself in a culture rich in history and innovation. Whether you're seeking adventure in the mountains or relaxation in hot springs, Japan offers an unforgettable journey.
                        """.trimIndent(),
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Justify
                    )
                }
                "France" -> {
                    Text(
                        text = """
                            France, the heart of romance and culture, beckons with its iconic landmarks and charming villages. Wander through the streets of Paris, marvel at the Eiffel Tower, and indulge in exquisite cuisine featuring croissants, wine, and cheese. Discover the lavender fields of Provence, the vineyards of Bordeaux, and the glamour of the French Riviera. From art museums to historic castles, France promises a blend of sophistication and natural beauty that will captivate your senses.
                        """.trimIndent(),
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Justify
                    )
                }
                "Hawaii" -> {
                    Text(
                        text = """
                            Hawaii, a tropical paradise in the Pacific, offers pristine beaches, volcanic landscapes, and a laid-back island vibe. Relax on the golden sands of Waikiki, hike through lush rainforests, or snorkel in crystal-clear waters teeming with marine life. Experience the rich Polynesian culture, enjoy fresh pineapple and poke bowls, and witness the majesty of active volcanoes. Whether you're seeking adventure or tranquility, Hawaii's aloha spirit and breathtaking scenery make it a dream destination for travelers.
                        """.trimIndent(),
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Justify
                    )
                }
            }
        }
    }
}