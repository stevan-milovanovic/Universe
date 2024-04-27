package rs.smobile.universe.ui.screen.planets

import androidx.paging.PagingData
import androidx.paging.testing.asSnapshot
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import rs.smobile.universe.AppDispatcherRule
import rs.smobile.universe.data.local.model.Planet
import rs.smobile.universe.data.repository.PlanetRepository

/**
 * Unit test for [PlanetsViewModel]
 */
class PlanetsViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val appDispatcherRule = AppDispatcherRule()

    @MockK
    private lateinit var planetRepository: PlanetRepository

    private lateinit var viewModel: PlanetsViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test get planets with successful response`(): Unit = runTest {
        val expectedResult = listOf(
            Planet(
                name = "Tatooine",
                diameter = "10465",
                climate = "arid",
                gravity = "1 standard",
                terrain = "desert",
                population = "200000",
                url = "https://swapi.dev/api/planets/1/"
            ),
            Planet(
                name = "Alderaan",
                diameter = "10465",
                climate = "arid",
                gravity = "1 standard",
                terrain = "desert",
                population = "200000",
                url = "https://swapi.dev/api/planets/2/"
            )
        )
        val pagingData = PagingData.from(expectedResult)
        coEvery { planetRepository.getPlanetsPagedFlow() } returns flowOf(pagingData)
        viewModel = PlanetsViewModel(planetRepository)

        coVerify { planetRepository.getPlanetsPagedFlow() }
        assertEquals(expectedResult, viewModel.pagingDataFlow.asSnapshot())
    }

    @Test(expected = Exception::class)
    fun `test get planets with exception thrown`(): Unit = runTest {
        val exception = Exception("Unexpected exception while trying to fetch planets")
        coEvery { planetRepository.getPlanetsPagedFlow() } throws exception
        viewModel = PlanetsViewModel(planetRepository)
    }

}