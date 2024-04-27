package rs.smobile.universe.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import rs.smobile.universe.data.network.NetworkDataSource
import rs.smobile.universe.data.network.UniverseRetrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Singleton
    @Binds
    abstract fun bindNetworkDataSource(dataSource: UniverseRetrofit): NetworkDataSource
}