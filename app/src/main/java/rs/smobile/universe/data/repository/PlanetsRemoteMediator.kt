package rs.smobile.universe.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import retrofit2.HttpException
import rs.smobile.universe.data.local.UniverseDatabase
import rs.smobile.universe.data.local.model.Planet
import rs.smobile.universe.data.network.NetworkDataSource
import java.io.IOException
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
        return try {
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

            val (remotePlanetsCount, remotePlanets) = networkDataSource.getPlanets(page)
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
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    companion object {
        private const val INITIAL_PAGE = 1
        const val PAGE_SIZE = 10
    }

}