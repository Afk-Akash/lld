package org.bookMyShow.model

data class Screen(
    val id: String,
    val seats: MutableList<Seat>
)
