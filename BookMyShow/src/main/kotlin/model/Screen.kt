package org.bookMyShow.model

import org.bookMyShow.enums.SeatCategory

data class Screen(
    val id: String,
    val seatCount: Map<SeatCategory, Int>
)
