package rs.smobile.universe.data.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import rs.smobile.universe.data.local.model.Planet

/**
 * Interface to the [Planet] data layer.
 */
interface PlanetRepository {
    fun getPlanetsPagedFlow(): Flow<PagingData<Planet>>
}