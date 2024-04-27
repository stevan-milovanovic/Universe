package rs.smobile.universe.ui.screen.planet

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import rs.smobile.universe.data.local.model.Planet
import rs.smobile.universe.ui.dimension.Padding
import rs.smobile.universe.ui.theme.UniverseTheme

@VisibleForTesting
const val PLANET_DETAILS_CARD_TEST_TAG = "PlanetDetailsCardTestTag"

@Composable
fun PlanetScreen(
    planet: Planet,
    onBackClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Padding.small)
            .clickable { onBackClick() }
            .testTag(PLANET_DETAILS_CARD_TEST_TAG)
    ) {
        Column {
            Text(
                text = planet.name,
                modifier = Modifier
                    .padding(
                        start = Padding.medium,
                        end = Padding.medium,
                        top = Padding.medium
                    )
            )
            Text(
                text = planet.terrain,
                modifier = Modifier
                    .padding(
                        start = Padding.medium,
                        end = Padding.medium,
                        top = Padding.small,
                        bottom = Padding.medium
                    )
            )
        }
    }
}

@Preview
@Composable
private fun PlanetScreenPreview() {
    val planet = Planet(
        name = "Alderaan",
        diameter = "10465",
        climate = "arid",
        gravity = "1 standard",
        terrain = "desert",
        population = "200000",
        url = "https://swapi.dev/api/planets/2/"
    )
    UniverseTheme {
        PlanetScreen(planet = planet) {}
    }
}