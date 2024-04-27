package rs.smobile.universe.data.repository

import rs.smobile.universe.data.network.NetworkDataSource
import rs.smobile.universe.data.network.model.Planet
import javax.inject.Inject

class PlanetRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource
) : PlanetRepository {

    override suspend fun getPlanets(): List<Planet> {
        val (count, planets) = networkDataSource.getPlanets(page = 1)
        return planets
    }
}