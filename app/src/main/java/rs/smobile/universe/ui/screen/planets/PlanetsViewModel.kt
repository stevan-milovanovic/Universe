package rs.smobile.universe.ui.screen.planets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import rs.smobile.universe.data.network.model.Planet
import rs.smobile.universe.data.repository.PlanetRepository
import javax.inject.Inject


@HiltViewModel
class PlanetsViewModel @Inject constructor(
    planetRepository: PlanetRepository,
) : ViewModel() {

    private var _planets: MutableStateFlow<List<Planet>> = MutableStateFlow(listOf())
    val planets: StateFlow<List<Planet>> = _planets

    init {
        viewModelScope.launch {
            _planets.update {
                planetRepository.getPlanets()
            }
        }
    }
}