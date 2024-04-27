package rs.smobile.universe.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import rs.smobile.universe.data.local.model.Planet

@Dao
interface PlanetDao {

    /**
     * Load all planets paged
     */
    @Query("SELECT * FROM planet")
    fun loadAllPlanetsPaged(): PagingSource<Int, Planet>

    /**
     * Delete all planets.
     */
    @Query("DELETE FROM planet")
    suspend fun deleteAll()

    /**
     * Insert or update planets in the database. If a planet already exists, replace it.
     *
     * @param planets the planets to be inserted or updated.
     */
    @Upsert
    suspend fun upsertAll(planets: List<Planet>)

    @Query("SELECT COUNT(name) FROM planet")
    fun getPlanetsCount(): Int

}