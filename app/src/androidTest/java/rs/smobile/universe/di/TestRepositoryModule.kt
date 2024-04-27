package rs.smobile.universe.di

import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import rs.smobile.universe.data.repository.FakePlanetRepository
import rs.smobile.universe.data.repository.PlanetRepository


@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class],
)
internal interface TestRepositoryModule {
    @Binds
    fun bindPlanetRepository(
        fakePlanetRepository: FakePlanetRepository,
    ): PlanetRepository
}