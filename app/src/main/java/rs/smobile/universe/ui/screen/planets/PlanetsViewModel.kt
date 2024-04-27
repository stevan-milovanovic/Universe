package rs.smobile.universe.ui.screen.planets

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import rs.smobile.universe.data.local.model.Planet
import rs.smobile.universe.data.repository.PlanetRepository
import javax.inject.Inject


@HiltViewModel
class PlanetsViewModel @Inject constructor(
    planetRepository: PlanetRepository,
) : ViewModel() {

    val pagingDataFlow: Flow<PagingData<Planet>> = planetRepository
        .getPlanetsPagedFlow()

}