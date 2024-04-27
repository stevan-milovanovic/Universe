package rs.smobile.universe.data.repository

import rs.smobile.universe.data.network.model.Planet

/**
 * Interface to the [Planet] data layer.
 */
interface PlanetRepository {
    suspend fun getPlanets(): List<Planet>
}