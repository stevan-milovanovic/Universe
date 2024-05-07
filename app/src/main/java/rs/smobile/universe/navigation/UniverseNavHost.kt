package rs.smobile.universe.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost


@Composable
fun UniverseNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = PLANETS_ROUTE,
        modifier = modifier,
    ) {
        planetsScreen(
            onPlanetClick = navController::navigateToPlanet
        )
        planetScreen()
    }
}