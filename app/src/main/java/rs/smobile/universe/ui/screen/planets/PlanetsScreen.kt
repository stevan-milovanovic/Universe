package rs.smobile.universe.ui.screen.planets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import rs.smobile.universe.data.network.model.Planet
import rs.smobile.universe.ui.dimension.Padding
import rs.smobile.universe.ui.theme.UniverseTheme

@Composable
fun PlanetsScreen(
    planets: List<Planet>,
    onPlanetClick: (String) -> Unit
) {
    LazyColumn {
        items(planets) { planet ->
            PlanetRow(
                planet = planet,
                onPlanetClick = onPlanetClick
            )
        }
    }
}

@Composable
private fun PlanetRow(
    planet: Planet,
    onPlanetClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Padding.small)
            .clickable { onPlanetClick(planet.url) }
    ) {
        Text(
            text = planet.name,
            modifier = Modifier
                .padding(Padding.medium)
        )
    }
}

@Preview
@Composable
private fun PlanetsScreenPreview() {
    UniverseTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            PlanetsScreen(
                planets = listOf(
                    Planet(
                        name = "Tatooine",
                        diameter = "10465",
                        climate = "arid",
                        gravity = "1 standard",
                        terrain = "desert",
                        population = "200000",
                        url = "https://swapi.dev/api/planets/1/"
                    ),
                    Planet(
                        name = "Alderaan",
                        diameter = "10465",
                        climate = "arid",
                        gravity = "1 standard",
                        terrain = "desert",
                        population = "200000",
                        url = "https://swapi.dev/api/planets/2/"
                    ),
                ),
                onPlanetClick = {}
            )
        }
    }
}