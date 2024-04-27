package rs.smobile.universe.ui.screen.planets

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import kotlinx.coroutines.flow.flowOf
import rs.smobile.universe.data.local.model.Planet
import rs.smobile.universe.ui.dimension.Padding
import rs.smobile.universe.ui.theme.UniverseTheme

@Composable
fun PlanetsScreen(
    planetsLazyPagingItems: LazyPagingItems<Planet>,
    onPlanetClick: (String) -> Unit
) {
    val state = rememberLazyListState()

    LazyColumn(
        state = state,
    ) {
        items(
            count = planetsLazyPagingItems.itemCount,
            key = planetsLazyPagingItems.itemKey { it.name },
            contentType = planetsLazyPagingItems.itemContentType { "Planets" }
        ) { index: Int ->
            val planet = planetsLazyPagingItems[index]

            if (planet != null) {
                PlanetRow(
                    index = index,
                    planet = planet,
                    onPlanetClick = onPlanetClick
                )
            }
        }
    }
}

@VisibleForTesting
const val PLANET_ROW_CARD_TEST_TAG = "PlanetRowCardTestTag"

@Composable
private fun PlanetRow(
    index: Int,
    planet: Planet,
    onPlanetClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Padding.small)
            .clickable { onPlanetClick(planet.name) }
            .testTag(PLANET_ROW_CARD_TEST_TAG + index)
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
    val testPagingData = PagingData.from(
        listOf(
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
        )
    )
    UniverseTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            PlanetsScreen(
                planetsLazyPagingItems = flowOf(testPagingData).collectAsLazyPagingItems(),
                onPlanetClick = {}
            )
        }
    }
}