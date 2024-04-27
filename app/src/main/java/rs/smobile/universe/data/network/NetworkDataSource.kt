package rs.smobile.universe.data.network

import rs.smobile.universe.data.network.model.Planet

/**
 * Interface representing network calls to the Planets backend
 */
interface NetworkDataSource {
    suspend fun getPlanets(page: Int): Pair<Int, List<Planet>>
}