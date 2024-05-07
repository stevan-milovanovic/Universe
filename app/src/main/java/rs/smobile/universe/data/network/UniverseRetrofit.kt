package rs.smobile.universe.data.network

import android.util.Log
import com.squareup.moshi.JsonClass
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import rs.smobile.universe.data.network.model.Planet
import javax.inject.Inject
import javax.inject.Singleton

interface PlanetsNetworkApi {
    @GET(value = "planets")
    suspend fun getPlanets(
        @Query("page") page: Int
    ): Response<NetworkResponse<Planet>>
}

@JsonClass(generateAdapter = true)
data class NetworkResponse<T>(
    val count: Int,
    val results: List<T>
)

/**
 * [Retrofit] backed [NetworkDataSource]
 */
@Singleton
class UniverseRetrofit @Inject constructor(
    private val planetsNetworkApi: PlanetsNetworkApi
) : NetworkDataSource {

    companion object {
        private const val TAG = "Universe Network Layer"
    }

    override suspend fun getPlanets(page: Int): NetworkResult {
        return try {
            val response = planetsNetworkApi.getPlanets(page)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                NetworkResult.Success(result.count to result.results)
            } else {
                NetworkResult.Success(0 to listOf())
            }
        } catch (e: Exception) {
            Log.e(TAG, "Unexpected exception while trying to fetch planets", e)
            NetworkResult.Error(e)
        }
    }
}

sealed class NetworkResult {
    data class Success(val data: Pair<Int, List<Planet>>) : NetworkResult()
    data class Error(val exception: Exception) : NetworkResult()
}