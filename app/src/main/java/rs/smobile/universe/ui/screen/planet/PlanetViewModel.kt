package rs.smobile.universe.ui.screen.planet

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import rs.smobile.universe.data.repository.PlanetRepository
import rs.smobile.universe.navigation.PlanetArgs
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class PlanetViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    planetRepository: PlanetRepository,
) : ViewModel() {

    private val planetArgs: PlanetArgs = PlanetArgs(savedStateHandle)

    private var planetName: String = planetArgs.planetName

    val planetFlow = planetRepository.getPlanetFlow(planetName)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null,
        )

    val actionsFlow = MutableStateFlow<PlanetScreenAction?>(null)

    fun addSnackbarAction() {
        viewModelScope.launch {
            planetFlow.value?.detailedData?.let { message ->
                actionsFlow.emit(
                    PlanetScreenAction.ShowSnackbarAction(
                        uuid = UUID.randomUUID(),
                        message = message
                    )
                )
            }
        }
    }

}