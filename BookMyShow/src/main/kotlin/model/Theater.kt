package org.bookMyShow.model

import org.bookMyShow.enums.City

data class Theater(
    val id: String,
    val theaterName: String,
    val city: City,
    val screens: List<Screen>,
    val movieShows: List<MovieShow>
)
