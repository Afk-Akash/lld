package org.bookMyShow.model

import org.bookMyShow.enums.SeatCategory

data class Seat(
    val seatNo: String,
    val seatCategory: SeatCategory,
)
