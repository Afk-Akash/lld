package org.bookMyShow.model

data class ShowSeat(
    val seat: Seat,
    val movieShowId: String,
    var isBooked: Boolean = false
)
