package rs.smobile.universe.data.network

/**
 * Interface representing network calls to the Planets backend
 */
interface NetworkDataSource {
    suspend fun getPlanets(page: Int): NetworkResult
}