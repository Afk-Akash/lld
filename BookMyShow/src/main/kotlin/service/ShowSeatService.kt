package org.bookMyShow.service

import org.bookMyShow.model.MovieShow
import org.bookMyShow.model.ShowSeat

class ShowSeatService(
    allShows: List<MovieShow>
): ShowSeatInterface {
    private val seatStorage: MutableMap<String, List<ShowSeat>> =
        allShows
            .associate { movieShow -> movieShow.id to generateShowSeats(movieShow).toMutableList() }
            .toMutableMap()


    private fun generateShowSeats(movieShow: MovieShow): List<ShowSeat> {
        return movieShow.screen.seats
            .map { seat ->  ShowSeat(seat = seat, movieShowId = movieShow.id) }
    }
    override fun getSeatAvailability(movieShowId: String): List<ShowSeat> {
        return seatStorage[movieShowId]?.filter { !it.isBooked } ?: emptyList()
    }

    override fun bookSeat(movieShowId: String, seats: List<ShowSeat>): Boolean {
        seats.forEach { requestedSeat ->
            seatStorage[movieShowId]?.forEach {
                if (it == requestedSeat){
                    it.isBooked = true
                }
            }
        }
        return true
    }

}