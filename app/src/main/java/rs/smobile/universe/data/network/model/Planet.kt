package rs.smobile.universe.data.network.model

import com.squareup.moshi.JsonClass
import rs.smobile.universe.data.local.model.Planet as LocalPlanet

@JsonClass(generateAdapter = true)
data class Planet(
    val name: String,
    val diameter: String,
    val climate: String,
    val gravity: String,
    val terrain: String,
    val population: String,
    val url: String
) {
    fun toLocal() = LocalPlanet(
        name,
        diameter,
        climate,
        gravity,
        terrain,
        population,
        url
    )
}
