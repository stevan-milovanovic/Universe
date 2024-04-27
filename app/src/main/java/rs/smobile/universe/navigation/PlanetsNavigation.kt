package rs.smobile.universe.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import rs.smobile.universe.ui.screen.planets.PlanetsScreen
import rs.smobile.universe.ui.screen.planets.PlanetsViewModel


const val PLANETS_ROUTE = "planets_route"

fun NavGraphBuilder.planetsScreen(
    onPlanetClick: (String) -> Unit
) {
    composable(
        route = PLANETS_ROUTE
    ) {
        val viewModel: PlanetsViewModel = hiltViewModel()
        val planets = viewModel.planets.collectAsStateWithLifecycle()

        PlanetsScreen(
            planets = planets.value,
            onPlanetClick = onPlanetClick
        )
    }
}