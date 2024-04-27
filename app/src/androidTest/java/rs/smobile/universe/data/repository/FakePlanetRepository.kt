package rs.smobile.universe.data.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import rs.smobile.universe.data.local.model.Planet
import javax.inject.Inject

class FakePlanetRepository @Inject constructor() : PlanetRepository {
    override fun getPlanetsPagedFlow(): Flow<PagingData<Planet>> = flowOf(
        PagingData.from(PlanetsProvider.planets)
    )

    override fun getPlanetFlow(planetName: String): Flow<Planet> =
        flowOf(PlanetsProvider.planets.first())
}