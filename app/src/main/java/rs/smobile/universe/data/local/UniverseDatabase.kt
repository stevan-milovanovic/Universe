package rs.smobile.universe.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import rs.smobile.universe.data.local.dao.PlanetDao
import rs.smobile.universe.data.local.model.Planet

@Database(entities = [Planet::class], version = 1, exportSchema = false)
abstract class UniverseDatabase : RoomDatabase() {

    abstract fun planetDao(): PlanetDao

}