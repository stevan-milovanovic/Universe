package rs.smobile.universe.data.repository

import rs.smobile.universe.data.local.model.Planet


object PlanetsProvider {
    val planets = listOf(
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
}