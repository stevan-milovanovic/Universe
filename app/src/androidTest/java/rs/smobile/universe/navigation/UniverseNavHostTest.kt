package rs.smobile.universe.navigation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import rs.smobile.universe.MainActivity
import rs.smobile.universe.data.repository.PlanetsProvider


@HiltAndroidTest
class UniverseNavHostTest {

    /**
     * Manages the components' state and is used to perform injection on your test
     */
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() = hiltRule.inject()

    /**
     * Use the primary activity to initialize the app normally.
     */
    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun firstScreenIsPlanetsScreen() {
        composeTestRule.apply {
            PlanetsProvider.planets.forEach { planet ->
                onNodeWithText(planet.name).assertIsDisplayed()
            }
        }
    }

}