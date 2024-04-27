package rs.smobile.universe.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import rs.smobile.universe.data.local.dao.PlanetDao
import rs.smobile.universe.data.local.model.Planet
import rs.smobile.universe.data.repository.PlanetsRemoteMediator.Companion.PAGE_SIZE
import javax.inject.Inject

class PlanetRepositoryImpl @Inject constructor(
    private val localDataSource: PlanetDao,
    private val remoteMediator: PlanetsRemoteMediator,
) : PlanetRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getPlanetsPagedFlow(): Flow<PagingData<Planet>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE),
        remoteMediator = remoteMediator
    ) {
        localDataSource.loadAllPlanetsPaged()
    }.flow
}