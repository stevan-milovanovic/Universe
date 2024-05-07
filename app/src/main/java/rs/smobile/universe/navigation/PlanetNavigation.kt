package rs.smobile.universe.navigation

import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import kotlinx.coroutines.flow.asStateFlow
import rs.smobile.universe.ui.screen.common.LoadingScreen
import rs.smobile.universe.ui.screen.planet.PlanetScreen
import rs.smobile.universe.ui.screen.planet.PlanetViewModel


@VisibleForTesting
const val PLANET_NAME_ARG = "planetName"

const val PLANET_ROUTE = "planet_route"

class PlanetArgs(val planetName: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(checkNotNull<String>(savedStateHandle[PLANET_NAME_ARG]))
}

fun NavController.navigateToPlanet(planetName: String) {
    navigate("$PLANET_ROUTE/$planetName") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.planetScreen() {
    composable(
        route = "$PLANET_ROUTE/{$PLANET_NAME_ARG}",
        arguments = listOf(
            navArgument(PLANET_NAME_ARG) { type = NavType.StringType },
        ),
    ) {
        val viewModel: PlanetViewModel = hiltViewModel()
        val planet by viewModel.planetFlow.collectAsStateWithLifecycle()

        planet?.let {
            PlanetScreen(
                planet = it,
                onCardClick = viewModel::addSnackbarAction,
                planetScreenAction = viewModel.actionsFlow.asStateFlow()
            )
        } ?: LoadingScreen()
    }
}
