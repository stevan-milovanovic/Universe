package rs.smobile.universe.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "planet")
data class Planet(
    @PrimaryKey
    val name: String,
    val diameter: String,
    val climate: String,
    val gravity: String,
    val terrain: String,
    val population: String,
    val url: String
)
