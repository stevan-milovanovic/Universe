package rs.smobile.universe.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import rs.smobile.universe.data.local.UniverseDatabase
import rs.smobile.universe.data.local.model.Planet
import rs.smobile.universe.data.network.NetworkDataSource
import rs.smobile.universe.data.network.NetworkResult
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class PlanetsRemoteMediator @Inject constructor(
    private val universeDatabase: UniverseDatabase,
    private val networkDataSource: NetworkDataSource
) : RemoteMediator<Int, Planet>() {

    private var remotePlanetsCount = Int.MAX_VALUE
    private var localPlanetsCount = Int.MIN_VALUE

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Planet>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> INITIAL_PAGE
            LoadType.PREPEND -> {
                return MediatorResult.Success(endOfPaginationReached = true)
            }

            LoadType.APPEND -> {
                if (localPlanetsCount < remotePlanetsCount) {
                    localPlanetsCount / PAGE_SIZE + 1
                } else {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
            }
        }

        return when (val networkResult = networkDataSource.getPlanets(page)) {
            is NetworkResult.Error -> MediatorResult.Error(networkResult.exception)
            is NetworkResult.Success -> handleSuccessfulNetworkResult(networkResult, loadType)
        }
    }

    private suspend fun handleSuccessfulNetworkResult(
        networkResult: NetworkResult.Success,
        loadType: LoadType
    ): MediatorResult {
        val (remotePlanetsCount, remotePlanets) = networkResult.data

        this@PlanetsRemoteMediator.remotePlanetsCount = remotePlanetsCount

        universeDatabase.withTransaction {
            if (loadType == LoadType.REFRESH) {
                universeDatabase.planetDao().deleteAll()
            }
            val planets = remotePlanets.map { it.toLocal() }
            universeDatabase.planetDao().upsertAll(planets)
            localPlanetsCount = universeDatabase.planetDao().getPlanetsCount()
        }

        return MediatorResult.Success(
            endOfPaginationReached = remotePlanetsCount == localPlanetsCount
        )
    }

    companion object {
        private const val INITIAL_PAGE = 1
        const val PAGE_SIZE = 10
    }

}