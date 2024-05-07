package rs.smobile.universe.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
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
        val planetsLazyPagingItems = viewModel.pagingDataFlow.collectAsLazyPagingItems()

        PlanetsScreen(
            planetsLazyPagingItems = planetsLazyPagingItems,
            onPlanetClick = onPlanetClick,
        )
    }
}