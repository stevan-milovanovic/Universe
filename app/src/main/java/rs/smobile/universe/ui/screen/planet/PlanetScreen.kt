package rs.smobile.universe.ui.screen.planet

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import rs.smobile.universe.data.local.model.Planet
import rs.smobile.universe.ui.dimension.Padding
import rs.smobile.universe.ui.theme.UniverseTheme
import java.util.UUID

@VisibleForTesting
const val PLANET_DETAILS_CARD_TEST_TAG = "PlanetDetailsCardTestTag"

sealed class PlanetScreenAction {
    data class ShowSnackbarAction(
        val uuid: UUID,
        val message: String
    ) : PlanetScreenAction()
}

@Composable
fun PlanetScreen(
    planet: Planet,
    onCardClick: () -> Unit,
    planetScreenAction: StateFlow<PlanetScreenAction?>
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        planetScreenAction.collectLatest { action ->
            when (action) {
                is PlanetScreenAction.ShowSnackbarAction -> snackbarHostState.showSnackbar(action.message)
                null -> {}
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { contentPadding ->
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Padding.large)
                .clickable { onCardClick() }
                .testTag(PLANET_DETAILS_CARD_TEST_TAG)
        ) {
            Column(modifier = Modifier.padding(contentPadding)) {
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
        PlanetScreen(
            planet = planet,
            onCardClick = {},
            planetScreenAction = MutableStateFlow(
                PlanetScreenAction.ShowSnackbarAction(
                    UUID.randomUUID(),
                    "message"
                )
            )
        )
    }
}