package org.bookMyShow.service

import org.bookMyShow.model.Seat
import org.bookMyShow.model.ShowSeat

interface ShowSeatInterface {
    fun getSeatAvailability(movieShowId: String): List<ShowSeat>

    fun bookSeat(movieShowId: String, seats: List<ShowSeat>): Boolean
}