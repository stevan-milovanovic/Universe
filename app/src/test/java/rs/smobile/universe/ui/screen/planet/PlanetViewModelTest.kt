package rs.smobile.universe.ui.screen.planet

import androidx.lifecycle.SavedStateHandle
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import rs.smobile.universe.AppDispatcherRule
import rs.smobile.universe.data.local.model.Planet
import rs.smobile.universe.data.repository.PlanetRepository
import rs.smobile.universe.navigation.PLANET_NAME_ARG


/**
 * Unit test for [PlanetViewModel]
 */
class PlanetViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val appDispatcherRule = AppDispatcherRule()

    @MockK
    private lateinit var savedStateHandle: SavedStateHandle

    @MockK
    private lateinit var planetRepository: PlanetRepository

    private lateinit var viewModel: PlanetViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test get planet flow`(): Unit = runTest {
        val planetName = "Tatooine"
        val expectedResult = Planet(
            name = planetName,
            diameter = "10465",
            climate = "arid",
            gravity = "1 standard",
            terrain = "desert",
            population = "200000",
            url = "https://swapi.dev/api/planets/1/"
        )
        every { savedStateHandle.get<String>(PLANET_NAME_ARG) } returns planetName
        coEvery { planetRepository.getPlanetFlow(planetName) } returns flowOf(expectedResult)
        viewModel = PlanetViewModel(savedStateHandle, planetRepository)

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.planetFlow.collect() }

        verify { savedStateHandle.get<String>(PLANET_NAME_ARG) }
        coVerify { planetRepository.getPlanetFlow(planetName) }
        Assert.assertEquals(expectedResult, viewModel.planetFlow.value)

        collectJob.cancel()
    }

}