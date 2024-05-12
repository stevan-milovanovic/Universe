package rs.smobile.universe.navigation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import rs.smobile.universe.MainActivity
import rs.smobile.universe.data.repository.PlanetsProvider
import rs.smobile.universe.ui.screen.planet.PLANET_DETAILS_CARD_TEST_TAG
import rs.smobile.universe.ui.screen.planets.PLANET_ROW_CARD_TEST_TAG


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

    @Test
    fun testNavigationFromListToDetailsScreen() {
        composeTestRule.apply {
            val planet = PlanetsProvider.planets.first()
            val index = 0

            onNodeWithText(planet.name)
                .assertExists()
                .assertIsDisplayed()
            onNodeWithText(planet.terrain)
                .assertDoesNotExist()

            onNodeWithTag(PLANET_ROW_CARD_TEST_TAG + index)
                .performClick()

            onNodeWithText(planet.name)
                .assertExists()
                .assertIsDisplayed()
            onNodeWithText(planet.terrain)
                .assertExists()
                .assertIsDisplayed()

            onNodeWithTag(PLANET_DETAILS_CARD_TEST_TAG).performClick()

            composeTestRule.onNode(
                hasText(planet.detailedData),
                useUnmergedTree = true
            ).assertIsDisplayed()
        }
    }

}