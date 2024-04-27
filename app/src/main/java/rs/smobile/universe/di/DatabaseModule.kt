package rs.smobile.universe.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import rs.smobile.universe.data.local.UniverseDatabase
import rs.smobile.universe.data.local.dao.PlanetDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): UniverseDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            UniverseDatabase::class.java,
            "Universe.db"
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun providePlanetDao(database: UniverseDatabase): PlanetDao = database.planetDao()

}