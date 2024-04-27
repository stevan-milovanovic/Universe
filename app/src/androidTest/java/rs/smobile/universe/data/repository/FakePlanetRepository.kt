package rs.smobile.universe.data.repository

import rs.smobile.universe.data.network.model.Planet
import javax.inject.Inject

class FakePlanetRepository @Inject constructor() : PlanetRepository {
    override suspend fun getPlanets(): List<Planet> = PlanetsProvider.planets
}