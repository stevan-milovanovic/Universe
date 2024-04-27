package rs.smobile.universe.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import rs.smobile.universe.data.repository.PlanetRepository
import rs.smobile.universe.data.repository.PlanetRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindPlanetRepository(repository: PlanetRepositoryImpl): PlanetRepository
}